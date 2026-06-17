package by.rom.notificationservice.service;

import by.rom.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    public void sendOrderCreatedEmail(
            OrderCreatedEvent event) {

        log.info(
                "Sending email to {}",
                event.customerEmail());

    }
}
