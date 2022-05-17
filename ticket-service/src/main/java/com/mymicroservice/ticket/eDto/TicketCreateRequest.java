package com.mymicroservice.ticket.eDto;


import java.time.LocalDate;

public record TicketCreateRequest(
        String description,
        String notes,
        String assigneeAccountId,
        String priorityType,
        String ticketStatus,
        LocalDate ticketDate
) {
}
