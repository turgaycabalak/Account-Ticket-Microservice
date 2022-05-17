package com.mymicroservice.notification.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Document
public class Notification {

    @Id
    private String id;
    private String ticketId;
    private String accountId;
    private String ticketDescription;
    private LocalDateTime sentAt;


    public Notification(String ticketId,
                        String accountId,
                        String ticketDescription,
                        LocalDateTime sentAt) {
        this.ticketId = ticketId;
        this.accountId = accountId;
        this.ticketDescription = ticketDescription;
        this.sentAt = sentAt;
    }
}
