package com.logistics.shopifytechnicalchallengesubmission.inventory.controller;

import com.logistics.shopifytechnicalchallengesubmission.inventory.controller.request.ItemRequest;
import com.logistics.shopifytechnicalchallengesubmission.inventory.model.Item;
import com.logistics.shopifytechnicalchallengesubmission.inventory.service.ItemService;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;

@RestController
@RequestMapping(path = "/items")
public class ItemController {

    private static final String ITEMS_CSV_FILENAME = "items.csv";
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<Item> listAllItems() {
        return itemService.getAllItems();
    }

    @GetMapping("/csv")
    public void getItemsCSV(HttpServletResponse response) throws Exception {
        response.setContentType("text/csv");
        response.setHeader(CONTENT_DISPOSITION, "attachment; filename=\"" + ITEMS_CSV_FILENAME + "\"");
        StatefulBeanToCsv<Item> csvWriter = new StatefulBeanToCsvBuilder<Item>(response.getWriter()).build();
        csvWriter.write(itemService.getAllItems());
    }

    @PostMapping
    public void createItem(@RequestBody ItemRequest itemRequest) {
        itemService.saveItem(itemRequest);
    }

    @PutMapping("/{id}")
    public void updateItem(@PathVariable Long id, @RequestBody ItemRequest itemRequest) {
        itemService.updateItem(id, itemRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
    }
}
