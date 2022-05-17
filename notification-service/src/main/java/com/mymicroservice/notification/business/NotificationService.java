package com.mymicroservice.notification.business;

import com.mymicroservice.clients.notification.TicketNotification;
import com.mymicroservice.notification.dataAccess.NotificationRepository;
import com.mymicroservice.notification.entities.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;


    public void sendTicketNotification(TicketNotification ticketNotification) {
        notificationRepository.save(new Notification(
                ticketNotification.getTicketId(),
                ticketNotification.getAccountId(),
                ticketNotification.getTicketDescription(),
                LocalDateTime.now()
        ));
    }

}
