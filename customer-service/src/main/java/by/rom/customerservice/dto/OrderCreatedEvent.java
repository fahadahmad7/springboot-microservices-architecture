package by.rom.customerservice.dto;

import java.math.BigDecimal;

public record OrderCreatedEvent(

        Long orderId,

        String customerName,

        String customerEmail,

        String productName,

        Integer price,

        Integer quantity

) {
}
