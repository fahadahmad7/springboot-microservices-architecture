package by.rom.customerservice.model;

import jakarta.persistence.EntityListeners;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.MappedSuperclass;
import java.time.Instant;

@MappedSuperclass
//@MappedSuperclass tells JPA:
//        “This class is NOT a database table by itself,
//        but its fields should be inherited by child entity classes.”
@Data
@EntityListeners(AuditingEntityListener.class)
public class AuditEntity {

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant modifiedAt;
}
