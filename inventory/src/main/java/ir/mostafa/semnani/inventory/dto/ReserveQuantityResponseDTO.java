package ir.mostafa.semnani.inventory.dto;

public record ReserveQuantityResponseDTO(
        Long remainedQuantity,
        String result,
        boolean isSuccessful
) {
}
