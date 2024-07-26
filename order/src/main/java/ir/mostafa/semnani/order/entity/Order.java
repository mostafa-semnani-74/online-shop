package ir.mostafa.semnani.order.entity;

import ir.mostafa.semnani.order.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long productId;

    private Long customerId;

    private Long quantity;

    private OrderStatus status;
}
