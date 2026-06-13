package by.rom.customerservice.service;

import by.rom.customerservice.client.InventoryClient;
import by.rom.customerservice.dto.InventoryResponse;
import by.rom.customerservice.exception.ProductOutOfStockException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    private final InventoryClient inventoryClient;

    @Retry(name = "inventoryRetry")
    @CircuitBreaker(
            name = "inventoryCircuitBreaker",
            fallbackMethod = "inventoryFallback"
    )
    public InventoryResponse findProduct(
            String nameProduct) {

        log.info(
                "Calling Inventory Service"
        );

        return inventoryClient.sendRequest(
                nameProduct
        );
    }

    public InventoryResponse inventoryFallback(
            String nameProduct,
            Exception ex) {

        log.error(
                "Inventory Service unavailable: {}",
                ex.getMessage()
        );

        throw new ProductOutOfStockException(
                "Inventory Service is currently unavailable"
        );
    }
}