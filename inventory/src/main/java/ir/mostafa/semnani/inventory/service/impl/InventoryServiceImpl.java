package ir.mostafa.semnani.inventory.service.impl;

import ir.mostafa.semnani.inventory.dto.request.InventoryDTO;
import ir.mostafa.semnani.inventory.dto.request.ReleaseQuantityRequestDTO;
import ir.mostafa.semnani.inventory.dto.response.ReleaseQuantityResponseDTO;
import ir.mostafa.semnani.inventory.dto.response.ReserveQuantityResponseDTO;
import ir.mostafa.semnani.inventory.entity.Inventory;
import ir.mostafa.semnani.inventory.mapper.InventoryMapper;
import ir.mostafa.semnani.inventory.repository.InventoryRepository;
import ir.mostafa.semnani.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
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
        Inventory inventory = findByProductId(inventoryDTO.productId());

        Long remainedQuantity;
        if (inventoryDTO.quantity() <= inventory.getQuantity()) {
            remainedQuantity = inventory.getQuantity() - inventoryDTO.quantity();
            inventory.setQuantity(remainedQuantity);
            inventoryRepository.save(inventory);
        } else {
            throw new RuntimeException("have not enough quantity for product id : " + inventory.getProductId() +
                    " with quantity : " + inventory.getQuantity());
        }

        log.info("{} reserved quantity for product with id : {}", inventoryDTO.quantity(), inventory.getProductId());
        return new ReserveQuantityResponseDTO(remainedQuantity);
    }

    @Override
    public ReleaseQuantityResponseDTO releaseQuantity(ReleaseQuantityRequestDTO requestDTO) {
        Inventory inventory = findByProductId(requestDTO.productId());
        log.info("trying to release quantity for product with id : {} and quantity : {}",
                inventory.getProductId(),
                inventory.getQuantity());

        Long increasedQuantity = inventory.getQuantity() + requestDTO.orderQuantity();
        inventory.setQuantity(increasedQuantity);

        inventoryRepository.save(inventory);
        log.info("increase quantity by {} for product with id : {}", requestDTO.orderQuantity(), inventory.getProductId());

        return new ReleaseQuantityResponseDTO(increasedQuantity);
    }

    Inventory findByProductId(Long productId) {
        return inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("cant check quantity , product not found with id : " + productId));
    }

}
