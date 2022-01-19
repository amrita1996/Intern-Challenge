package com.logistics.shopifytechnicalchallengesubmission.inventory.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logistics.shopifytechnicalchallengesubmission.inventory.controller.request.InventoryRequest;
import com.logistics.shopifytechnicalchallengesubmission.inventory.model.Inventory;
import com.logistics.shopifytechnicalchallengesubmission.inventory.service.InventoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(InventoryController.class)
class InventoryControllerTest {

    @MockBean
    private InventoryService inventoryService;

    @Autowired
    private MockMvc mvc;

    @Test
    void givenInventory_whenGetAll_thenReturnJsonArray() throws Exception {
        final List<Inventory> inventoryList = getInventory();
        when(inventoryService.getAll()).thenReturn(inventoryList);

        String json = "[" +
                "{" +
                "\"inventoryId\":1," +
                "\"itemId\":3," +
                "\"warehouseId\":2," +
                "\"quantity\":100," +
                "\"modifiedDate\":\"2022-02-02\"" +
                "}," +
                "{" +
                "\"inventoryId\":2," +
                "\"itemId\":6," +
                "\"warehouseId\":4," +
                "\"quantity\":200," +
                "\"modifiedDate\":\"2022-04-04\"" +
                "}" +
                "]";

        final String mvcResult = mvc.perform(get("/inventory")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertEquals(json, mvcResult);
    }

    @Test
    void givenInventoryRequest_whenCreateInventory_thenReturnCreateMethodCalled() throws Exception {
        InventoryRequest request = new InventoryRequest(100, 1L, 2L);

        mvc.perform(post("/inventory")
                .content(new ObjectMapper().writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Mockito.verify(inventoryService).saveInventory(request);
    }

    @Test
    void givenInventoryExists_whenUpdateInventory_thenReturnUpdateMethodCalled() throws Exception {
        InventoryRequest request = new InventoryRequest(100, 1L, 2L);

        mvc.perform(put("/inventory/1")
                .content(new ObjectMapper().writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Mockito.verify(inventoryService).updateInventory(1L, request);
    }

    @Test
    void givenInventoryExists_whenDeleteInventory_thenReturnDeleteMethodCalled() throws Exception {
        mvc.perform(delete("/inventory/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Mockito.verify(inventoryService).deleteInventory(1L);
    }

    @Test
    void givenInventory_whenExportCSV_thenReturnSuccess() throws Exception {
        mvc.perform(get("/inventory/csv")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Mockito.verify(inventoryService).getAll();
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