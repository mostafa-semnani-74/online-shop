package ir.mostafa.semnani.order.dto;

public record ReleaseQuantityRequestDTO(
        Long productId,
        Long orderQuantity
) {
}
