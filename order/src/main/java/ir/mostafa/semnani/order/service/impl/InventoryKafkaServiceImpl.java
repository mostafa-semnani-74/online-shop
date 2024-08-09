package ir.mostafa.semnani.order.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.mostafa.semnani.order.dto.PublishQuantityReservedEventDTO;
import ir.mostafa.semnani.order.entity.Order;
import ir.mostafa.semnani.order.enums.OrderStatus;
import ir.mostafa.semnani.order.repository.OrderRepository;
import ir.mostafa.semnani.order.service.InventoryKafkaService;
import ir.mostafa.semnani.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryKafkaServiceImpl implements InventoryKafkaService {

    private final OrderRepository orderRepository;

    private final OrderService orderService;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "inventory",
            groupId = "order_inventory_reserve_quantity_group")
    @Override
    public void handleQuantityReservedEvent(String quantityReservedEvent) {
        PublishQuantityReservedEventDTO publishQuantityReservedEventDTO;
        try {
            publishQuantityReservedEventDTO = objectMapper.readValue(quantityReservedEvent, PublishQuantityReservedEventDTO.class);
        } catch (JsonProcessingException e) {
            log.error("error in handleQuantityReservedEvent in parsing message to PublishQuantityReservedEventDTO", e);
            throw new RuntimeException(e);
        }

        Order orderEntity = orderService.findById(publishQuantityReservedEventDTO.orderId());
        orderEntity.setStatus(OrderStatus.DONE);

        orderRepository.save(orderEntity);
    }

}
