package net.nvsoftware.OrderService.service;

import lombok.extern.log4j.Log4j2;
import net.nvsoftware.OrderService.entity.OrderEntity;
import net.nvsoftware.OrderService.model.OrderRequest;
import net.nvsoftware.OrderService.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;


    @Override
    public long placeOrder(OrderRequest orderRequest) {
        log.info("Start: OrderService placeOrder");
        OrderEntity orderEntity = OrderEntity.builder()
                        .productId(orderRequest.getProductId())
                        .quantity(orderRequest.getQuantity())
                        .totalAmount(orderRequest.getTotalAmount())
                        .orderStatus("Created")
                        .orderDate(Instant.now())
                        .build();

        orderRepository.save(orderEntity);
        log.info("Process: OrderService  placeOrder Order Entity has been saved with id: " + orderEntity.getId());
        //use order service
        log.info("End: OrderService placeOrder");
        return 0;
    }
}
