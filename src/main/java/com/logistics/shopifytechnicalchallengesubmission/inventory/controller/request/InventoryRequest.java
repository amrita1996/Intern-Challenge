package com.logistics.shopifytechnicalchallengesubmission.inventory.controller.request;

import java.util.Objects;

public class InventoryRequest {
    private Integer quantity;
    private Long itemId;
    private Long warehouseId;

    public InventoryRequest(Integer quantity, Long itemId, Long warehouseId) {
        this.quantity = quantity;
        this.itemId = itemId;
        this.warehouseId = warehouseId;
    }

    public InventoryRequest() {
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryRequest that = (InventoryRequest) o;
        return Objects.equals(quantity, that.quantity) && Objects.equals(itemId, that.itemId) && Objects.equals(warehouseId, that.warehouseId);
    }
}
