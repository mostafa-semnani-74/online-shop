package ir.mostafa.semnani.inventory.service.impl;

import ir.mostafa.semnani.inventory.dto.InventoryDTO;
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
    public Boolean checkHaveEnoughQuantityByProductId(Long productId, Long requestedQuantity) {
        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("cant check quantity , product not found with id : " + productId));
        return requestedQuantity <= inventory.getQuantity() ;
    }

    @Override
    public InventoryDTO save(InventoryDTO inventoryDTO) {
        return InventoryMapper.toDTO(inventoryRepository.save(InventoryMapper.toEntity(inventoryDTO)));
    }
}
