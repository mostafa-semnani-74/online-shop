package ir.mostafa.semnani.inventory.service;

import ir.mostafa.semnani.inventory.dto.request.InventoryDTO;
import ir.mostafa.semnani.inventory.dto.request.ReleaseQuantityRequestDTO;
import ir.mostafa.semnani.inventory.dto.response.ReleaseQuantityResponseDTO;
import ir.mostafa.semnani.inventory.dto.response.ReserveQuantityResponseDTO;

import java.util.List;

public interface InventoryService {
    List<InventoryDTO> findAll();

    InventoryDTO save(InventoryDTO inventoryDTO);

    ReserveQuantityResponseDTO reserveQuantity(InventoryDTO inventoryDTO);

    ReleaseQuantityResponseDTO releaseQuantity(ReleaseQuantityRequestDTO requestDTO);

}
