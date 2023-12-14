package net.nvsoftware.OrderService.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long productId;
    private long quantity;
    private long totalAmount;
    private Instant orderDate;
    private String orderStatus;

}
