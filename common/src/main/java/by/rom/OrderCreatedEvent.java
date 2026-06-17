package by.rom;

public record OrderCreatedEvent(

        Long orderId,

        String customerName,

        String customerEmail,

        String productName,

        Integer price,

        Integer quantity

) {
}
