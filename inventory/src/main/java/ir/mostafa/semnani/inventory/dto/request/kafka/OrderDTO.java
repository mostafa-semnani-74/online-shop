package ir.mostafa.semnani.inventory.dto.request.kafka;

import ir.mostafa.semnani.inventory.enums.OrderStatus;

public record OrderDTO(
        Long id,
        Long productId,
        Long customerId,
        Long quantity,
        OrderStatus status
) {
}
