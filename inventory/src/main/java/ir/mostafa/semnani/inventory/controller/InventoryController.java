package ir.mostafa.semnani.inventory.controller;

import ir.mostafa.semnani.inventory.dto.request.InventoryDTO;
import ir.mostafa.semnani.inventory.dto.request.ReleaseQuantityRequestDTO;
import ir.mostafa.semnani.inventory.dto.response.ReleaseQuantityResponseDTO;
import ir.mostafa.semnani.inventory.dto.response.ReserveQuantityResponseDTO;
import ir.mostafa.semnani.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventories")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    public ResponseEntity<List<InventoryDTO>> findAll() {
        return ResponseEntity.ok(inventoryService.findAll());
    }

    @PostMapping
    public ResponseEntity<InventoryDTO> save(@RequestBody InventoryDTO inventoryDTO) {
        return new ResponseEntity<>(inventoryService.save(inventoryDTO), HttpStatus.CREATED);
    }

    @PostMapping("reserve-quantity")
    public ResponseEntity<ReserveQuantityResponseDTO> reserveQuantity(@RequestBody InventoryDTO inventoryDTO) {
        return ResponseEntity.ok(inventoryService.reserveQuantity(inventoryDTO));
    }

    @PostMapping("release-quantity")
    public ResponseEntity<ReleaseQuantityResponseDTO> releaseQuantity(@RequestBody ReleaseQuantityRequestDTO requestDTO) {
        return ResponseEntity.ok(inventoryService.releaseQuantity(requestDTO));
    }

}
