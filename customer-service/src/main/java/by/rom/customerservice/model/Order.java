package by.rom.customerservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "customer")
@EqualsAndHashCode(callSuper = false, exclude = "customer")
public class Order extends AuditEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameProduct;

    private String information;

    private int countOfProduct;

    private int price;

    @ManyToOne
    @JsonBackReference     //ignored in json
    private Customer customer;
}
