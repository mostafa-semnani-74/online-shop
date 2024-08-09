package ir.mostafa.semnani.order.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.mostafa.semnani.order.dto.InventoryDTO;
import ir.mostafa.semnani.order.dto.OrderDTO;
import ir.mostafa.semnani.order.dto.ReleaseQuantityRequestDTO;
import ir.mostafa.semnani.order.dto.ReserveQuantityResponseDTO;
import ir.mostafa.semnani.order.entity.Order;
import ir.mostafa.semnani.order.enums.OrderStatus;
import ir.mostafa.semnani.order.mapper.OrderMapper;
import ir.mostafa.semnani.order.repository.OrderRepository;
import ir.mostafa.semnani.order.service.OrderKafkaService;
import ir.mostafa.semnani.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {
    private static final String INVENTORY_BASE_URL = "http://inventory/api/v1/inventories";

    private final OrderRepository orderRepository;

    private final WebClient.Builder webClientBuilder;

    private final OrderMapper orderMapper;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private final OrderKafkaService orderKafkaService;

    @Override
    public OrderDTO save(OrderDTO orderDTO) {
//        InventoryDTO inventoryDTO = new InventoryDTO(null,
//                orderDTO.productId(),
//                orderDTO.quantity());
//
//        webClientBuilder.build()
//                .post()
//                .uri(INVENTORY_BASE_URL + "/reserve-quantity")
//                .bodyValue(inventoryDTO)
//                .retrieve()
//                .bodyToMono(ReserveQuantityResponseDTO.class)
//                .block();
//        log.info("{} quantity reserved for order with product Id {}", orderDTO.quantity(), orderDTO.productId());

        Order orderEntity = orderMapper.toEntity(orderDTO);
        orderEntity.setStatus(OrderStatus.IN_PROGRESS);
        orderRepository.save(orderEntity);
        log.info("order created : {}", orderEntity);

        OrderDTO orderResponseDTO = orderMapper.toDTO(orderEntity);

        try {
            orderKafkaService.publishOrderCreatedEvent(objectMapper.writeValueAsString(orderResponseDTO));
        } catch (JsonProcessingException e) {
            log.error("error in parsing order dto to message for sending to kafka in save order service", e);
            throw new RuntimeException(e.getMessage());
        }

        return orderResponseDTO;
    }

    @Override
    public OrderDTO cancel(Long id) {
        Order order = findById(id);
        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);

        ReleaseQuantityRequestDTO releaseQuantityRequestDTO =
                new ReleaseQuantityRequestDTO(order.getProductId(), order.getQuantity());

        webClientBuilder.build()
                .post()
                .uri(INVENTORY_BASE_URL + "/release-quantity")
                .bodyValue(releaseQuantityRequestDTO)
                .retrieve()
                .bodyToMono(ReserveQuantityResponseDTO.class)
                .block();
        log.info("{} quantity released for order with id {} , product id {}",
                order.getQuantity(), order.getId(), order.getProductId());

        return orderMapper.toDTO(order);
    }

    @Override
    public List<OrderDTO> findAll() {
        return orderMapper.toDTOs(orderRepository.findAll());
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("order not find with id " + id));
    }

}
