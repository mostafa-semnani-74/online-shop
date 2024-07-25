package ir.mostafa.semnani.inventory.dto.request;

public record ReleaseQuantityRequestDTO(
        Long productId,
        Long orderQuantity
) {
}
