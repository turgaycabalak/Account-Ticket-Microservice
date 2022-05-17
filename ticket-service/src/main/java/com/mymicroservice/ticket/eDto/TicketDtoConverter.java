package com.mymicroservice.ticket.eDto;

import com.mymicroservice.ticket.entities.PriorityType;
import com.mymicroservice.ticket.entities.Ticket;
import com.mymicroservice.ticket.entities.TicketStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class TicketDtoConverter {

    public Ticket convertToTicket(TicketCreateRequest request) {
        return new Ticket(
                request.description(),
                request.notes(),
                request.assigneeAccountId(),
                PriorityType.valueOf(request.priorityType()),
                TicketStatus.valueOf(request.ticketStatus()),
                request.ticketDate(),
                LocalDateTime.now()
        );
    }

    public TicketResponse convertToTicketResponse(Ticket ticket) {
        return new TicketResponse(
                ticket.getId(),
                ticket.getDescription(),
                ticket.getNotes(),
                ticket.getAssigneeAccountId(),
                ticket.getPriorityType().name(),
                ticket.getTicketStatus().name(),
                ticket.getTicketDate(),
                ticket.getCreatedAt(),
                ticket.getUpdatedAt()
        );
    }

    public List<TicketResponse> convertToTicketResponseList(List<Ticket> ticketList) {
        return ticketList.stream()
                .map(this::convertToTicketResponse)
                .toList();
    }
}
