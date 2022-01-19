package com.logistics.shopifytechnicalchallengesubmission.inventory.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logistics.shopifytechnicalchallengesubmission.inventory.controller.request.ItemRequest;
import com.logistics.shopifytechnicalchallengesubmission.inventory.model.Item;
import com.logistics.shopifytechnicalchallengesubmission.inventory.service.ItemService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ItemController.class)
class ItemControllerTest {

    @MockBean
    private ItemService itemService;

    @Autowired
    private MockMvc mvc;

    @Test
    void givenItems_whenGetItems_thenReturnJsonArray() throws Exception {
        final List<Item> items = getItems();
        when(itemService.getAllItems()).thenReturn(items);

        String json = "[" +
                "{" +
                "\"itemId\":1," +
                "\"description\":\"White Tank Top\"," +
                "\"sku\":\"WHI-TAN-TOP-124642\"," +
                "\"categoryId\":1," +
                "\"purchasedPrice\":2.97," +
                "\"manufacturedDate\":\"2021-02-11\"," +
                "\"warrantyDays\":400," +
                "\"createdDate\":\"2022-01-16\"" +
                "}," +
                "{" +
                "\"itemId\":2," +
                "\"description\":\"Black Tank Top\"," +
                "\"sku\":\"BLA-TAN-TOP-124642\"," +
                "\"categoryId\":1," +
                "\"purchasedPrice\":4.97," +
                "\"manufacturedDate\":\"2021-03-11\"," +
                "\"warrantyDays\":300," +
                "\"createdDate\":\"2022-03-16\"" +
                "}" +
                "]";

        final String mvcResult = mvc.perform(get("/items")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertEquals(json, mvcResult);
    }

    @Test
    void givenItemRequest_whenCreateItems_thenReturnCreateMethodCalled() throws Exception {
        ItemRequest request = new ItemRequest("White Tank Top", "WHI-TAN-TOP-124642", 1,
                BigDecimal.valueOf(2.97), Date.valueOf("2021-02-11"), 2);

        mvc.perform(post("/items")
                .content(new ObjectMapper().writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Mockito.verify(itemService).saveItem(request);
    }

    @Test
    void givenItemExists_whenUpdateItems_thenReturnUpdateMethodCalled() throws Exception {
        ItemRequest request = new ItemRequest("White Tank Top", "WHI-TAN-TOP-124642", 1,
                BigDecimal.valueOf(2.97), Date.valueOf("2021-02-11"), 2);

        mvc.perform(put("/items/1")
                .content(new ObjectMapper().writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Mockito.verify(itemService).updateItem(1L, request);
    }

    @Test
    void givenItemExists_whenDeleteItems_thenReturnDeleteMethodCalled() throws Exception {
        mvc.perform(delete("/items/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Mockito.verify(itemService).deleteItem(1L);
    }

    @Test
    void givenItems_whenExportCSV_thenReturnSuccess() throws Exception {
        mvc.perform(get("/items/csv")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Mockito.verify(itemService).getAllItems();
    }


    private List<Item> getItems() {
        Item item1 = new Item();
        item1.setItemId(1L);
        item1.setDescription("White Tank Top");
        item1.setSku("WHI-TAN-TOP-124642");
        item1.setCategoryId(1);
        item1.setPurchasedPrice(BigDecimal.valueOf(2.97));
        item1.setManufacturedDate(Date.valueOf("2021-02-11"));
        item1.setWarrantyDays(400);
        item1.setCreatedDate(Date.valueOf("2022-01-16"));

        Item item2 = new Item();
        item2.setItemId(2L);
        item2.setDescription("Black Tank Top");
        item2.setSku("BLA-TAN-TOP-124642");
        item2.setCategoryId(1);
        item2.setPurchasedPrice(BigDecimal.valueOf(4.97));
        item2.setManufacturedDate(Date.valueOf("2021-03-11"));
        item2.setWarrantyDays(300);
        item2.setCreatedDate(Date.valueOf("2022-03-16"));
        return asList(item1, item2);
    }

}