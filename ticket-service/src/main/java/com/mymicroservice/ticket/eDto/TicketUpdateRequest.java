package com.mymicroservice.ticket.eDto;

public record TicketUpdateRequest(
        String description,
        String notes,
        String priorityType,
        String ticketStatus
) {
}
