package ir.mostafa.semnani.inventory.service.impl;

import ir.mostafa.semnani.inventory.dto.InventoryDTO;
import ir.mostafa.semnani.inventory.dto.ReserveQuantityResponseDTO;
import ir.mostafa.semnani.inventory.entity.Inventory;
import ir.mostafa.semnani.inventory.mapper.InventoryMapper;
import ir.mostafa.semnani.inventory.repository.InventoryRepository;
import ir.mostafa.semnani.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;

    @Override
    public List<InventoryDTO> findAll() {
        return InventoryMapper.toDTOs(inventoryRepository.findAll());
    }

    @Override
    public InventoryDTO save(InventoryDTO inventoryDTO) {
        return InventoryMapper.toDTO(inventoryRepository.save(InventoryMapper.toEntity(inventoryDTO)));
    }

    @Override
    public ReserveQuantityResponseDTO reserveQuantity(InventoryDTO inventoryDTO) {
        Inventory inventory = inventoryRepository.findByProductId(inventoryDTO.productId())
                .orElseThrow(() -> new RuntimeException("cant check quantity , product not found with id : " + inventoryDTO.productId()));

        Long remainedQuantity;
        if (inventoryDTO.quantity() <= inventory.getQuantity()) {
            remainedQuantity = inventory.getQuantity() - inventoryDTO.quantity();
            inventory.setQuantity(remainedQuantity);
            inventoryRepository.save(inventory);
        } else {
            throw new RuntimeException("have not enough quantity for product id : " + inventory.getProductId() +
                    " with quantity : " + inventory.getQuantity());
        }

        return new ReserveQuantityResponseDTO(
                remainedQuantity,
                "success",
                true
        );
    }

}
