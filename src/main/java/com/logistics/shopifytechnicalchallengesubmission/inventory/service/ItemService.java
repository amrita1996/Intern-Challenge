package com.logistics.shopifytechnicalchallengesubmission.inventory.service;

import com.logistics.shopifytechnicalchallengesubmission.inventory.controller.request.ItemRequest;
import com.logistics.shopifytechnicalchallengesubmission.inventory.model.Item;
import com.logistics.shopifytechnicalchallengesubmission.inventory.repository.ItemRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

import static java.time.LocalDate.now;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Transactional
    public void saveItem(ItemRequest itemRequest) {
        saveItemDB(itemRequest, null);
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Transactional
    public void updateItem(Long id, ItemRequest itemRequest) {
        validateIdPresence(id);
        saveItemDB(itemRequest, id);
    }

    @Transactional
    public void deleteItem(Long id) {
        validateIdPresence(id);
        itemRepository.deleteById(id);
    }

    private void validateIdPresence(Long id) {
        itemRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("Illegal id provided");
        });
    }

    private Item saveItemDB(ItemRequest itemRequest, Long itemId) {
        Item item = new Item();
        item.setItemId(itemId);
        item.setDescription(itemRequest.getDescription());
        item.setSku(itemRequest.getSku());
        item.setCategoryId(itemRequest.getCategoryId());
        item.setPurchasedPrice(itemRequest.getPurchasedPrice());
        item.setManufacturedDate(itemRequest.getManufacturedDate());
        item.setWarrantyDays(itemRequest.getWarrantyDays());
        item.setCreatedDate(Date.valueOf(now()));
        return itemRepository.save(item);
    }
}
