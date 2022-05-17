package com.mymicroservice.ticket.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
@Data
@NoArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;
    private String description;
    private String notes;
    private String assigneeAccountId;
    @Enumerated(EnumType.ORDINAL)
    private PriorityType priorityType;
    @Enumerated(EnumType.ORDINAL)
    private TicketStatus ticketStatus;
    private LocalDate ticketDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public Ticket(String description,
                  String notes,
                  String assigneeAccountId,
                  PriorityType priorityType,
                  TicketStatus ticketStatus,
                  LocalDate ticketDate,
                  LocalDateTime createdAt) {

        this.description = description;
        this.notes = notes;
        this.assigneeAccountId = assigneeAccountId;
        this.priorityType = priorityType;
        this.ticketStatus = ticketStatus;
        this.ticketDate = ticketDate;
        this.createdAt = createdAt;
    }
}
