package ir.mostafa.semnani.order.dto;

import ir.mostafa.semnani.order.enums.OrderStatus;

public record OrderDTO(
        Long id,
        Long productId,
        Long customerId,
        Long quantity,
        OrderStatus status
) {
}
