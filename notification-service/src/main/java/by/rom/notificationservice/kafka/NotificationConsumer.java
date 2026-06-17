package by.rom.notificationservice.kafka;

import by.rom.OrderCreatedEvent;
import by.rom.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationConsumer {

    private final NotificationService notificationService;

    @KafkaListener(
            topics = "order-created-topic",
            groupId = "notification-group")
    public void consume(OrderCreatedEvent event) {

        notificationService.sendOrderCreatedEmail(event);

    }
}