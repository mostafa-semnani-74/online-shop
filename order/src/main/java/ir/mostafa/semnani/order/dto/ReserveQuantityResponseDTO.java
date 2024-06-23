package ir.mostafa.semnani.order.dto;

public record ReserveQuantityResponseDTO(
        Long remainedQuantity,
        String result,
        boolean isSuccessful
) {
}
