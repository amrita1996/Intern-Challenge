package com.logistics.shopifytechnicalchallengesubmission.inventory.controller;

import com.logistics.shopifytechnicalchallengesubmission.inventory.controller.request.InventoryRequest;
import com.logistics.shopifytechnicalchallengesubmission.inventory.model.Inventory;
import com.logistics.shopifytechnicalchallengesubmission.inventory.service.InventoryService;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;

@RestController
@RequestMapping(path = "/inventory")
public class InventoryController {

    private static final String INVENTORY_FILE_NAME = "inventory.csv";
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public List<Inventory> getAllInventory() {
        return inventoryService.getAll();
    }

    @GetMapping("/csv")
    public void getItemsCSV(HttpServletResponse response) throws Exception {
        response.setContentType("text/csv");
        response.setHeader(CONTENT_DISPOSITION, "attachment; filename=\"" + INVENTORY_FILE_NAME + "\"");
        StatefulBeanToCsv<Inventory> csvWriter = new StatefulBeanToCsvBuilder<Inventory>(response.getWriter()).build();
        csvWriter.write(inventoryService.getAll());
    }

    @PostMapping
    public void createItem(@RequestBody InventoryRequest inventoryRequest) {
        inventoryService.saveInventory(inventoryRequest);
    }

    @PutMapping("/{id}")
    public void updateItem(@PathVariable Long id, @RequestBody InventoryRequest inventoryRequest) {
        inventoryService.updateInventory(id, inventoryRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        inventoryService.deleteInventory(id);
    }
}
