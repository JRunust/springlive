package net.nvsoftware.OrderService.service;

import lombok.extern.log4j.Log4j2;
import net.nvsoftware.OrderService.client.PaymentServiceFeignClient;
import net.nvsoftware.OrderService.client.ProductServiceFeignClient;
import net.nvsoftware.OrderService.entity.OrderEntity;
import net.nvsoftware.OrderService.model.OrderRequest;
import net.nvsoftware.OrderService.model.PaymentRequest;
import net.nvsoftware.OrderService.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductServiceFeignClient productServiceFeignClient;
    @Autowired
    private PaymentServiceFeignClient paymentServiceFeignClient;
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
        productServiceFeignClient.reduceQuantity(orderRequest.getProductId(),orderRequest.getQuantity());
        log.info("Process: OrderService  placeOrder feign client product service reduce quantity  " + orderEntity.getQuantity());

        PaymentRequest paymentRequest = PaymentRequest.builder()
                .orderId(orderEntity.getId())
                .paymentMode(orderRequest.getPaymentMode())
                .totalAmount(orderRequest.getTotalAmount())
                .build();
        String orderStatus = null;
        try{
            paymentServiceFeignClient.doPayment(paymentRequest);
            orderStatus = "PAID";
            log.info("Payment service do payment success");
        }catch (Exception e){
            orderStatus = "Payment Failed";
        }
       orderEntity.setOrderStatus(orderStatus);
        orderRepository.save(orderEntity);

        log.info("End: OrderService placeOrder done with order id " + orderEntity.getId());
        return orderEntity.getId();
    }
}
