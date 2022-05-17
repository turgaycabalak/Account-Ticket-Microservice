package com.mymicroservice.ticket.entities;

import lombok.Getter;

@Getter
public enum TicketStatus {
    OPEN("Open"),
    IN_PROGRESS("In Progress"),
    RESOLVED("Resolved"),
    CLOSED("Closed");

    private final String label;

    TicketStatus(String label) {
        this.label = label;
    }

}
