package by.rom.notificationservice.dto;

public record OrderCreatedEvent(

        Long orderId,

        String customerName,

        String customerEmail,

        String productName,

        Integer price,

        Integer quantity

) {
}
