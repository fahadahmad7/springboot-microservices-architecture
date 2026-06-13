package by.rom.customerservice.client;

import by.rom.customerservice.dto.InventoryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "INVENTORY-SERVICE")
public interface InventoryClient {

    @GetMapping("/api/product/inventory")
    InventoryResponse sendRequest(@RequestParam("nameProduct") String nameProduct);
}