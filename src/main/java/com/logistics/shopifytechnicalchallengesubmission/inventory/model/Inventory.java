package com.logistics.shopifytechnicalchallengesubmission.inventory.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long inventoryId;
    private Long itemId;
    private Long warehouseId;
    private Integer quantity;
    private Date modifiedDate;

    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Long getInventoryId() {
        return inventoryId;
    }

    public Long getItemId() {
        return itemId;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inventory inventory = (Inventory) o;
        return Objects.equals(inventoryId, inventory.inventoryId) && Objects.equals(itemId, inventory.itemId)
                && Objects.equals(warehouseId, inventory.warehouseId) && Objects.equals(quantity, inventory.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inventoryId, itemId, warehouseId, quantity);
    }
}
