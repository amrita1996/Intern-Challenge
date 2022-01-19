package com.logistics.shopifytechnicalchallengesubmission.inventory.service;

import com.logistics.shopifytechnicalchallengesubmission.inventory.controller.request.InventoryRequest;
import com.logistics.shopifytechnicalchallengesubmission.inventory.model.Inventory;
import com.logistics.shopifytechnicalchallengesubmission.inventory.model.Item;
import com.logistics.shopifytechnicalchallengesubmission.inventory.model.Warehouse;
import com.logistics.shopifytechnicalchallengesubmission.inventory.repository.InventoryRepository;
import com.logistics.shopifytechnicalchallengesubmission.inventory.repository.ItemRepository;
import com.logistics.shopifytechnicalchallengesubmission.inventory.repository.WarehouseRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class InventoryServiceTest {

    private static final long INVENTORY_ID = 1L;
    private static final long ITEM_ID = 3L;
    private static final long WAREHOUSE_ID = 2L;
    private static final String EXPECTED_INVENTORY_ERROR_MESSAGE = "Illegal inventory id provided";
    private static final String EXPECTED_ITEM_ERROR_MESSAGE = "Illegal item id provided";
    private static final String EXPECTED_WAREHOUSE_ERROR_MESSAGE = "Illegal warehouse id provided";
    private final InventoryRepository inventoryRepository = Mockito.mock(InventoryRepository.class);
    private final ItemRepository itemRepository = Mockito.mock(ItemRepository.class);
    private final WarehouseRepository warehouseRepository = Mockito.mock(WarehouseRepository.class);

    private InventoryService inventoryService = new InventoryService(inventoryRepository, itemRepository,
            warehouseRepository);

    @Test
    void shouldCallGetAllFunctionAndReturnItems_whenGetAllItems() {
        final List<Inventory> inventoryList = getInventory();

        Mockito.when(inventoryRepository.findAll()).thenReturn(inventoryList);

        final List<Inventory> allInventory = inventoryService.getAll();

        Mockito.verify(inventoryRepository).findAll();
        assertEquals(inventoryList, allInventory);
    }

    @Test
    void shouldCallSaveFunction_whenSaveItem() {
        InventoryRequest request = new InventoryRequest(100, ITEM_ID, WAREHOUSE_ID);
        final Inventory inventory = getInventory().get(0);
        inventory.setInventoryId(null);
        Mockito.when(itemRepository.findById(ITEM_ID)).thenReturn(Optional.of(new Item()));
        Mockito.when(warehouseRepository.findById(WAREHOUSE_ID)).thenReturn(Optional.of(new Warehouse()));

        inventoryService.saveInventory(request);

        Mockito.verify(inventoryRepository).save(inventory);
    }

    @Test
    void shouldCheckExistenceAndThrowError_whenSaveItem_itemDoesntExist() {
        InventoryRequest request = new InventoryRequest(100, ITEM_ID, WAREHOUSE_ID);
        final Inventory inventory = getInventory().get(0);
        Mockito.when(itemRepository.findById(ITEM_ID)).thenReturn(Optional.empty());
        Mockito.when(warehouseRepository.findById(WAREHOUSE_ID)).thenReturn(Optional.of(new Warehouse()));

        final String message = assertThrows(IllegalArgumentException.class, () -> {
            inventoryService.saveInventory(request);
        }).getMessage();

        Mockito.verify(inventoryRepository, Mockito.times(0)).save(inventory);
        assertEquals(EXPECTED_ITEM_ERROR_MESSAGE, message);
    }

    @Test
    void shouldCheckExistenceAndThrowError_whenSaveItem_warehouseDoesntExist() {
        InventoryRequest request = new InventoryRequest(100, ITEM_ID, WAREHOUSE_ID);
        final Inventory inventory = getInventory().get(0);
        Mockito.when(itemRepository.findById(ITEM_ID)).thenReturn(Optional.of(new Item()));
        Mockito.when(warehouseRepository.findById(WAREHOUSE_ID)).thenReturn(Optional.empty());

        final String message = assertThrows(IllegalArgumentException.class, () -> {
            inventoryService.saveInventory(request);
        }).getMessage();

        Mockito.verify(inventoryRepository, Mockito.times(0)).save(inventory);
        assertEquals(EXPECTED_WAREHOUSE_ERROR_MESSAGE, message);
    }

    @Test
    void shouldCheckExistenceAndCallSaveFunction_whenUpdateItem() {
        InventoryRequest request = new InventoryRequest(100, ITEM_ID, WAREHOUSE_ID);
        final Inventory inventory = getInventory().get(0);
        Mockito.when(inventoryRepository.findById(INVENTORY_ID)).thenReturn(Optional.of(new Inventory()));
        Mockito.when(itemRepository.findById(ITEM_ID)).thenReturn(Optional.of(new Item()));
        Mockito.when(warehouseRepository.findById(WAREHOUSE_ID)).thenReturn(Optional.of(new Warehouse()));

        inventoryService.updateInventory(INVENTORY_ID, request);

        Mockito.verify(inventoryRepository).save(inventory);
    }

    @Test
    void shouldCheckExistenceAndThrowError_whenUpdateItem_itemDoesntExist() {
        InventoryRequest request = new InventoryRequest(100, ITEM_ID, WAREHOUSE_ID);
        final Inventory inventory = getInventory().get(0);
        Mockito.when(itemRepository.findById(INVENTORY_ID)).thenReturn(Optional.empty());

        final String message = assertThrows(IllegalArgumentException.class, () -> {
            inventoryService.updateInventory(INVENTORY_ID, request);
        }).getMessage();


        Mockito.verify(inventoryRepository, Mockito.times(0)).save(inventory);
        assertEquals(EXPECTED_INVENTORY_ERROR_MESSAGE, message);
    }

    @Test
    void shouldCheckExistenceAndCallDeleteFunction_whenDeleteItem() {
        final Inventory inventory = getInventory().get(0);

        Mockito.when(inventoryRepository.findById(INVENTORY_ID)).thenReturn(Optional.of(inventory));

        inventoryService.deleteInventory(INVENTORY_ID);

        Mockito.verify(inventoryRepository).deleteById(INVENTORY_ID);
    }

    @Test
    void shouldCheckExistenceAndThrowException_whenDeleteItem_ItemDoesntExist() {
        final Inventory inventory = getInventory().get(0);
        Mockito.when(inventoryRepository.findById(INVENTORY_ID)).thenReturn(Optional.empty());

        final String message = assertThrows(IllegalArgumentException.class, () -> {
            inventoryService.deleteInventory(INVENTORY_ID);
        }).getMessage();


        Mockito.verify(inventoryRepository, Mockito.times(0)).deleteById(INVENTORY_ID);
        assertEquals(EXPECTED_INVENTORY_ERROR_MESSAGE, message);
    }

    private List<Inventory> getInventory() {
        Inventory inventory1 = new Inventory();
        inventory1.setInventoryId(1L);
        inventory1.setWarehouseId(2L);
        inventory1.setItemId(3L);
        inventory1.setQuantity(100);
        inventory1.setModifiedDate(Date.valueOf("2022-02-02"));
        Inventory inventory2 = new Inventory();
        inventory2.setInventoryId(2L);
        inventory2.setWarehouseId(4L);
        inventory2.setItemId(6L);
        inventory2.setQuantity(200);
        inventory2.setModifiedDate(Date.valueOf("2022-04-04"));
        return asList(inventory1, inventory2);
    }
}