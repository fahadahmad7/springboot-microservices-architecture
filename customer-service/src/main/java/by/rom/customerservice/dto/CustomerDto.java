package by.rom.customerservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder // Lombok annotation
public class CustomerDto {
    private String email;
    private String phoneNumber;
    private String name;
}
