package com.mymicroservice.ticket.eDto.elastic;

import com.mymicroservice.ticket.entities.Ticket;
import com.mymicroservice.ticket.entities.elastic.TicketModel;
import org.springframework.stereotype.Component;

@Component
public class TicketModelDtoConverter {

    public TicketModel convertToTicketModel(Ticket ticket, String nameSurname) {
        return new TicketModel(
                ticket.getId(),
                ticket.getDescription(),
                ticket.getNotes(),
                nameSurname,
                ticket.getPriorityType().name(),
                ticket.getTicketStatus().name(),
                ticket.getTicketDate()

        );
    }
}
