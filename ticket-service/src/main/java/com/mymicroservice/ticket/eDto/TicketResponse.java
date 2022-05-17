package com.mymicroservice.ticket.eDto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record TicketResponse(
        String id,
        String description,
        String notes,
        String assigneeAccountId,
        String priorityType,
        String ticketStatus,
        LocalDate ticketDate,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
