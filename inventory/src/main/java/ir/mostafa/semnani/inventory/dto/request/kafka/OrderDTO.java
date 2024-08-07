package ir.mostafa.semnani.inventory.dto.request.kafka;

public record OrderDTO(
        Long id,
        Long productId,
        Long customerId,
        Long quantity
) {
}
