package com.mymicroservice.ticket.entities;

import lombok.Getter;

@Getter
public enum PriorityType {
    URGENT("Urgent"),
    LOW("Low"),
    HIGH("High");

    private final String label;

    PriorityType(String label) {
        this.label = label;
    }
}
