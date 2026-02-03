package by.rom.customerservice.client;
import by.rom.customerservice.dto.EmailDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationClient {

    private final RestTemplate restTemplate;

    public void sendEmail(EmailDto emailDto) {

        log.info("sending email to {} with text: {}",
                emailDto.getEmail(), emailDto.getBody());

        String url = "http://notification-service/api/notification/email";

        restTemplate.postForEntity(url, emailDto, Void.class);
    }
}

