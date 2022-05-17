package com.mymicroservice.ticket.business;

import com.mymicroservice.clients.account.AccountFeignClient;
import com.mymicroservice.clients.account.AccountResponse;
import com.mymicroservice.clients.notification.TicketNotification;
import com.mymicroservice.ticket.dataAccess.TicketRepository;
import com.mymicroservice.ticket.dataAccess.elastic.TicketElasticRepository;
import com.mymicroservice.ticket.eDto.TicketCreateRequest;
import com.mymicroservice.ticket.eDto.TicketDtoConverter;
import com.mymicroservice.ticket.eDto.TicketResponse;
import com.mymicroservice.ticket.eDto.TicketUpdateRequest;
import com.mymicroservice.ticket.eDto.elastic.TicketModelDtoConverter;
import com.mymicroservice.ticket.entities.PriorityType;
import com.mymicroservice.ticket.entities.Ticket;
import com.mymicroservice.ticket.entities.TicketStatus;
import com.mymicroservice.ticket.entities.elastic.TicketModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.mymicroservice.ticket.mqConfig.RabbitMQConfig.EXCHANGE;
import static com.mymicroservice.ticket.mqConfig.RabbitMQConfig.ROUTING_KEY;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final TicketDtoConverter ticketDtoConverter;
    private final TicketElasticRepository ticketElasticRepository;
    private final TicketModelDtoConverter ticketModelDtoConverter;
    private final AccountFeignClient accountFeignClient;
    private final RabbitTemplate template;

    @Transactional
    public void createTicket(TicketCreateRequest request) {

        AccountResponse accountResponse = accountFeignClient.getAccountById(request.assigneeAccountId()).getBody();

        //saving to rdb
        Ticket savedTicket = ticketRepository.save(ticketDtoConverter.convertToTicket(request));

        //saving to elastic search db
        String nameSurname = accountResponse.firstName() + " " + accountResponse.lastName();
        TicketModel ticketModel = ticketModelDtoConverter
                .convertToTicketModel(savedTicket, nameSurname);
        ticketElasticRepository.save(ticketModel);

        //sending TicketNotification to queue
        TicketNotification ticketNotification = new TicketNotification(
                savedTicket.getId(),
                request.assigneeAccountId(),
                savedTicket.getDescription()
        );
        template.convertAndSend(
                EXCHANGE,
                ROUTING_KEY,
                ticketNotification
        );
    }

    public TicketResponse updateTicket(String tickerId, TicketUpdateRequest updateRequest) {
        Ticket ticketFromDb = findTicketById(tickerId);
        ticketFromDb.setDescription(updateRequest.description());
        ticketFromDb.setNotes(updateRequest.notes());
        ticketFromDb.setPriorityType(PriorityType.valueOf(updateRequest.priorityType()));
        ticketFromDb.setTicketStatus(TicketStatus.valueOf(updateRequest.ticketStatus()));
        ticketFromDb.setUpdatedAt(LocalDateTime.now());

        return ticketDtoConverter
                .convertToTicketResponse(ticketRepository.save(ticketFromDb));
    }

    private Ticket findTicketById(String tickerId) {
        return ticketRepository.findById(tickerId)
                .orElseThrow(() -> new IllegalStateException("Ticket id not found: " + tickerId));
    }

    public TicketResponse getTicketById(String ticketId) {
        return ticketDtoConverter
                .convertToTicketResponse(findTicketById(ticketId));
    }

    public List<TicketResponse> getAllTickets(Pageable pageable) {
        return ticketDtoConverter
                .convertToTicketResponseList(ticketRepository.findAll(pageable).getContent());
    }
}
