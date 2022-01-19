package com.logistics.shopifytechnicalchallengesubmission.inventory.service;

import com.logistics.shopifytechnicalchallengesubmission.inventory.controller.request.ItemRequest;
import com.logistics.shopifytechnicalchallengesubmission.inventory.model.Item;
import com.logistics.shopifytechnicalchallengesubmission.inventory.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class ItemServiceTest {
    private static final long ITEM_ID = 1L;
    private static final String EXPECTED_ERROR_MESSAGE = "Illegal id provided";
    private final ItemRepository itemRepository = Mockito.mock(ItemRepository.class);

    private final ItemService itemService = new ItemService(itemRepository);

    @Test
    void shouldCallGetAllFunctionAndReturnItems_whenGetAllItems() {
        final List<Item> items = asList(getItem());
        Mockito.when(itemRepository.findAll()).thenReturn(items);

        final List<Item> allItems = itemService.getAllItems();

        Mockito.verify(itemRepository).findAll();
        assertEquals(items, allItems);
    }

    @Test
    void shouldCallSaveFunction_whenSaveItem() {
        ItemRequest request = new ItemRequest("White Tank Top", "WHI-TAN-TOP-124642", 1,
                BigDecimal.valueOf(2.97), Date.valueOf("2021-02-11"), 2);
        final Item item = getItem();

        itemService.saveItem(request);

        Mockito.verify(itemRepository).save(item);
    }

    @Test
    void shouldCheckExistenceAndCallSaveFunction_whenUpdateItem() {
        ItemRequest request = new ItemRequest("White Tank Top", "WHI-TAN-TOP-124642", 1,
                BigDecimal.valueOf(2.97), Date.valueOf("2021-02-11"), 2);
        final Item item = getItem();
        item.setItemId(ITEM_ID);
        Mockito.when(itemRepository.findById(ITEM_ID)).thenReturn(Optional.of(item));

        itemService.updateItem(ITEM_ID, request);

        Mockito.verify(itemRepository).save(item);
    }

    @Test
    void shouldCheckExistenceAndThrowError_whenUpdateItem_itemDoesntExist() {
        ItemRequest request = new ItemRequest("White Tank Top", "WHI-TAN-TOP-124642", 1,
                BigDecimal.valueOf(2.97), Date.valueOf("2021-02-11"), 2);
        final Item item = getItem();
        item.setItemId(ITEM_ID);
        Mockito.when(itemRepository.findById(ITEM_ID)).thenReturn(Optional.empty());

        final String message = assertThrows(IllegalArgumentException.class, () -> {
            itemService.updateItem(ITEM_ID, request);
        }).getMessage();


        Mockito.verify(itemRepository, Mockito.times(0)).save(item);
        assertEquals(EXPECTED_ERROR_MESSAGE, message);
    }

    @Test
    void shouldCheckExistenceAndCallDeleteFunction_whenDeleteItem() {
        final Item item = getItem();
        item.setItemId(ITEM_ID);
        Mockito.when(itemRepository.findById(ITEM_ID)).thenReturn(Optional.of(item));

        itemService.deleteItem(ITEM_ID);

        Mockito.verify(itemRepository).deleteById(ITEM_ID);
    }

    @Test
    void shouldCheckExistenceAndThrowException_whenDeleteItem_ItemDoesntExist() {
        final Item item = getItem();
        item.setItemId(ITEM_ID);
        Mockito.when(itemRepository.findById(ITEM_ID)).thenReturn(Optional.empty());

        final String message = assertThrows(IllegalArgumentException.class, () -> {
            itemService.deleteItem(ITEM_ID);
        }).getMessage();


        Mockito.verify(itemRepository, Mockito.times(0)).deleteById(ITEM_ID);
        assertEquals(EXPECTED_ERROR_MESSAGE, message);
    }

    private Item getItem() {
        Item item1 = new Item();
        item1.setDescription("White Tank Top");
        item1.setSku("WHI-TAN-TOP-124642");
        item1.setCategoryId(1);
        item1.setPurchasedPrice(BigDecimal.valueOf(2.97));
        item1.setManufacturedDate(Date.valueOf("2021-02-11"));
        item1.setWarrantyDays(2);
        return item1;
    }
}