package ir.mostafa.semnani.order.mapper;

import ir.mostafa.semnani.order.dto.OrderDTO;
import ir.mostafa.semnani.order.entity.Order;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {
    public static Order toEntity(OrderDTO dto) {
        if (dto == null)
            return null;

        return Order.builder()
                .id(dto.id())
                .productId(dto.productId())
                .quantity(dto.quantity())
                .build();
    }

    public static OrderDTO toDTO(Order entity) {
        if (entity == null)
            return null;

        return new OrderDTO(
                entity.getId(),
                entity.getProductId(),
                entity.getQuantity());
    }

    public static List<OrderDTO> toDTOs(List<Order> orders) {
        return orders.stream()
                .map(OrderMapper::toDTO)
                .collect(Collectors.toList());
    }

}
