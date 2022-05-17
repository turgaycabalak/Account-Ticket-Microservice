package com.mymicroservice.notification.rabbitmq;

import com.mymicroservice.clients.notification.TicketNotification;
import com.mymicroservice.notification.business.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class NotificationConsumer {

    private final NotificationService notificationService;

    @RabbitListener(queues = "ticket.queue")
    public void consumer(TicketNotification ticketNotification){

        log.info("Consumed {} from queue", ticketNotification);
        notificationService.sendTicketNotification(ticketNotification);
    }
}
