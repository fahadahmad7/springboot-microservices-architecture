package by.rom.customerservice.service;

import by.rom.customerservice.config.MessageConfig;
import by.rom.customerservice.dto.*;
import by.rom.customerservice.exception.*;
import by.rom.customerservice.model.Customer;
import by.rom.customerservice.model.Order;
import by.rom.customerservice.repository.CustomerRepository;
import by.rom.customerservice.repository.OrderRepository;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import by.rom.customerservice.client.InventoryClient;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;
    private final InventoryService inventoryService;

    @Transactional
    public Order createOrder(OrderDto orderDto) {
        Customer customer = findCustomer(orderDto);

        InventoryResponse response = inventoryService.findProduct(orderDto.getNameProduct());
        if (!response.isInStock()) {
            throw new ProductOutOfStockException("Product isn't in stock, please try again later");
        }
        if (orderDto.getCountOfProduct()>response.getQuantity()) {
            throw new InsufficientStockException("Insufficient stock, please try again later");
        }

        log.info("creating order: {}", orderDto);

        Order order = Order.builder()
                .information(orderDto.getInformation())
                .customer(customer)
                .nameProduct(orderDto.getNameProduct())
                .countOfProduct(orderDto.getCountOfProduct())
                .build();

        log.info("saving order to the DB: {}", orderDto);

        Order savedOrder;
        order.setPrice(response.getPrice());
        savedOrder  = orderRepository.save(order);
        log.info("saved order {}", savedOrder);
        sendEmail(savedOrder, customer);
        return savedOrder;
    }

    public List<Order> getOrders(CustomerDto customerDto) {
        List<Order> list = orderRepository.findOrderByCustomerNumberAndEmail(customerDto.getPhoneNumber());
        if (list.isEmpty()){
            throw new NoOrdersFoundForCustomerException("Customer doesn't have any orders, try again.");
        }
        else
            return list;
    }

    private Customer findCustomer(OrderDto orderDto) {
        return customerRepository.findById(orderDto.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException("customer not found with id: " + orderDto.getCustomerId()));
    }

    private void sendEmail(Order savedOrder, Customer customer) {

        OrderCreatedEvent event =
                new OrderCreatedEvent(
                        savedOrder.getId(),
                        customer.getName(),
                        customer.getEmail(),
                        savedOrder.getNameProduct(),
                        savedOrder.getPrice(),
                        savedOrder.getCountOfProduct()
                );

        kafkaTemplate.send(
                "order-created-topic",
                event);
        log.info("Event published {}", event);
    }
}
