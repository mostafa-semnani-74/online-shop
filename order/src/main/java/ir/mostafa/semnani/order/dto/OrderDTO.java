package ir.mostafa.semnani.order.dto;

public record OrderDTO(
        Long id,
        Long productId,
        Long customerId,
        Long quantity
) {
}
