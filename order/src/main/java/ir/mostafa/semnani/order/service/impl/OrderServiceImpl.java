package ir.mostafa.semnani.order.service.impl;

import ir.mostafa.semnani.order.dto.InventoryDTO;
import ir.mostafa.semnani.order.dto.OrderDTO;
import ir.mostafa.semnani.order.dto.ReserveQuantityResponseDTO;
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
        ReserveQuantityResponseDTO reserveQuantityResponseDTO = webClientBuilder.build()
                .post()
                .uri(INVENTORY_BASE_URL + "/reserve-quantity")
                .bodyValue(new InventoryDTO(null,
                        orderDTO.productId(),
                        orderDTO.quantity()))
                .retrieve()
                .bodyToMono(ReserveQuantityResponseDTO.class)
                .block();

        return toDTO(orderRepository.save(toEntity(orderDTO)));
    }

    @Override
    public List<OrderDTO> findAll() {
        return toDTOs(orderRepository.findAll());
    }

}
