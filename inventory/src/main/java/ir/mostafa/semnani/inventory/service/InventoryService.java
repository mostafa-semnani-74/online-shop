package ir.mostafa.semnani.inventory.service;

import ir.mostafa.semnani.inventory.dto.InventoryDTO;
import ir.mostafa.semnani.inventory.dto.ReserveQuantityResponseDTO;

import java.util.List;

public interface InventoryService {
    List<InventoryDTO> findAll();

    InventoryDTO save(InventoryDTO inventoryDTO);

    ReserveQuantityResponseDTO reserveQuantity(InventoryDTO inventoryDTO);

}
