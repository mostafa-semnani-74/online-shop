package ir.mostafa.semnani.inventory.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.mostafa.semnani.inventory.dto.request.InventoryDTO;
import ir.mostafa.semnani.inventory.dto.request.kafka.OrderDTO;
import ir.mostafa.semnani.inventory.dto.request.kafka.PublishQuantityReservedEventDTO;
import ir.mostafa.semnani.inventory.service.InventoryKafkaService;
import ir.mostafa.semnani.inventory.service.InventoryService;
import ir.mostafa.semnani.inventory.service.OrderKafkaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderKafkaServiceImpl implements OrderKafkaService {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private final InventoryService inventoryService;
    private final InventoryKafkaService inventoryKafkaService;

    @KafkaListener(topics = "order",
            groupId = "inventory_group")
    @Override
    public void handleOrderCreatedEvent(String orderCreatedEvent) {
        log.info("order created event : message received with body : {}", orderCreatedEvent);

        OrderDTO orderDTO;
        try {
            orderDTO = objectMapper.readValue(orderCreatedEvent, OrderDTO.class);
        } catch (JsonProcessingException e) {
            log.error("error in handleOrderCreatedEvent in parsing orderCreatedEvent json string to OrderDTO class", e);
            throw new RuntimeException(e);
        }

        InventoryDTO InventoryDTO = new InventoryDTO(null, orderDTO.productId(), orderDTO.quantity());
        inventoryService.reserveQuantity(InventoryDTO);

        PublishQuantityReservedEventDTO publishQuantityReservedEventDTO = new PublishQuantityReservedEventDTO(orderDTO.id());

        try {
            inventoryKafkaService.publishQuantityReservedEvent(objectMapper.writeValueAsString(publishQuantityReservedEventDTO));
        } catch (JsonProcessingException e) {
            log.error("error in handleOrderCreatedEvent in parsing reserveQuantityResponseDTO to json", e);
            throw new RuntimeException(e);
        }
    }

}
