package ir.mostafa.semnani.inventory.mapper;

import ir.mostafa.semnani.inventory.dto.request.InventoryDTO;
import ir.mostafa.semnani.inventory.entity.Inventory;

import java.util.List;
import java.util.stream.Collectors;

public class InventoryMapper {
    public static Inventory toEntity(InventoryDTO inventoryDTO) {
        return Inventory.builder()
                .productId(inventoryDTO.productId())
                .quantity(inventoryDTO.quantity())
                .build();
    }

    public static InventoryDTO toDTO(Inventory inventory) {
        return new InventoryDTO(inventory.getId(),
                inventory.getProductId(),
                inventory.getQuantity());
    }

    public static List<InventoryDTO> toDTOs(List<Inventory> inventories) {
        return inventories.stream()
                .map(InventoryMapper::toDTO)
                .collect(Collectors.toList());
    }

}
