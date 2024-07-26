package ir.mostafa.semnani.order.service.impl;

import ir.mostafa.semnani.order.dto.InventoryDTO;
import ir.mostafa.semnani.order.dto.OrderDTO;
import ir.mostafa.semnani.order.dto.ReleaseQuantityRequestDTO;
import ir.mostafa.semnani.order.dto.ReserveQuantityResponseDTO;
import ir.mostafa.semnani.order.entity.Order;
import ir.mostafa.semnani.order.enums.OrderStatus;
import ir.mostafa.semnani.order.repository.OrderRepository;
import ir.mostafa.semnani.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import static ir.mostafa.semnani.order.mapper.OrderMapper.*;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private static final String INVENTORY_BASE_URL = "http://inventory/api/v1/inventories";

    private final OrderRepository orderRepository;

    private final WebClient.Builder webClientBuilder;

    @Override
    public OrderDTO save(OrderDTO orderDTO) {
        InventoryDTO inventoryDTO = new InventoryDTO(null,
                orderDTO.productId(),
                orderDTO.quantity());

        webClientBuilder.build()
                .post()
                .uri(INVENTORY_BASE_URL + "/reserve-quantity")
                .bodyValue(inventoryDTO)
                .retrieve()
                .bodyToMono(ReserveQuantityResponseDTO.class)
                .block();
        log.info("{} quantity reserved for order with product Id {}", orderDTO.quantity(), orderDTO.productId());

        return toDTO(orderRepository.save(toEntity(orderDTO)));
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

        return toDTO(order);
    }

    @Override
    public List<OrderDTO> findAll() {
        return toDTOs(orderRepository.findAll());
    }

    private Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("order not find with id " + id));
    }

}
