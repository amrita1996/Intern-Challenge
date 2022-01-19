package com.logistics.shopifytechnicalchallengesubmission.inventory.service;

import com.logistics.shopifytechnicalchallengesubmission.inventory.controller.request.InventoryRequest;
import com.logistics.shopifytechnicalchallengesubmission.inventory.model.Inventory;
import com.logistics.shopifytechnicalchallengesubmission.inventory.repository.InventoryRepository;
import com.logistics.shopifytechnicalchallengesubmission.inventory.repository.ItemRepository;
import com.logistics.shopifytechnicalchallengesubmission.inventory.repository.WarehouseRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

import static java.time.LocalDate.now;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ItemRepository itemRepository;
    private final WarehouseRepository warehouseRepository;

    public InventoryService(InventoryRepository inventoryRepository, ItemRepository itemRepository, WarehouseRepository warehouseRepository) {
        this.inventoryRepository = inventoryRepository;
        this.itemRepository = itemRepository;
        this.warehouseRepository = warehouseRepository;
    }

    @Transactional
    public void saveInventory(InventoryRequest inventoryRequest) {
        validateItemPresence(inventoryRequest.getItemId());
        validateWarehousePresence(inventoryRequest.getWarehouseId());
        saveInventoryDB(inventoryRequest, null);
    }


    @Transactional
    public void updateInventory(Long id, InventoryRequest inventoryRequest) {
        validateInventoryPresence(id);
        saveInventoryDB(inventoryRequest, id);
    }

    @Transactional
    public void deleteInventory(Long id) {
        validateInventoryPresence(id);
        inventoryRepository.deleteById(id);
    }

    private void validateItemPresence(Long id) {
        itemRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("Illegal item id provided");
        });
    }

    private void validateInventoryPresence(Long id) {
        inventoryRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("Illegal inventory id provided");
        });
    }

    private void validateWarehousePresence(Long id) {
        warehouseRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("Illegal warehouse id provided");
        });
    }

    private void saveInventoryDB(InventoryRequest inventoryRequest, Long inventoryId) {
        Inventory inventory = new Inventory();
        inventory.setInventoryId(inventoryId);
        inventory.setItemId(inventoryRequest.getItemId());
        inventory.setQuantity(inventoryRequest.getQuantity());
        inventory.setWarehouseId(inventoryRequest.getWarehouseId());
        inventory.setModifiedDate(Date.valueOf(now()));
        inventoryRepository.save(inventory);
    }

    public List<Inventory> getAll() {
        return inventoryRepository.findAll();
    }
}
