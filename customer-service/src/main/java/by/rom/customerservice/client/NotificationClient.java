package by.rom.customerservice.client;

import by.rom.customerservice.dto.EmailDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "NOTIFICATION-SERVICE")
public interface NotificationClient {

    @PostMapping("/api/notification/email")
    void sendEmail(@RequestBody EmailDto emailDto);
}