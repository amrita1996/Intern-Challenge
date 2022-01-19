package com.logistics.shopifytechnicalchallengesubmission.inventory.model;

import com.opencsv.bean.CsvBindByPosition;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @CsvBindByPosition(position = 0)
    private Long itemId;
    @CsvBindByPosition(position = 1)
    private String description;
    @CsvBindByPosition(position = 2)
    private String sku;
    @CsvBindByPosition(position = 3)
    private Integer categoryId;
    @CsvBindByPosition(position = 4)
    private BigDecimal purchasedPrice;
    @CsvBindByPosition(position = 5)
    private Date manufacturedDate;
    @CsvBindByPosition(position = 6)
    private Integer warrantyDays;
    @CsvBindByPosition(position = 7)
    private Date createdDate;

    public Item() {
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public BigDecimal getPurchasedPrice() {
        return purchasedPrice;
    }

    public void setPurchasedPrice(BigDecimal purchasedPrice) {
        this.purchasedPrice = purchasedPrice;
    }

    public Date getManufacturedDate() {
        return manufacturedDate;
    }

    public void setManufacturedDate(Date manufacturedDate) {
        this.manufacturedDate = manufacturedDate;
    }

    public Integer getWarrantyDays() {
        return warrantyDays;
    }

    public void setWarrantyDays(Integer warrantyDays) {
        this.warrantyDays = warrantyDays;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(itemId, item.itemId) && Objects.equals(description, item.description) && Objects.equals(sku, item.sku) && Objects.equals(categoryId, item.categoryId) && Objects.equals(purchasedPrice, item.purchasedPrice) && Objects.equals(manufacturedDate, item.manufacturedDate) && Objects.equals(warrantyDays, item.warrantyDays);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, description, sku, categoryId, purchasedPrice, manufacturedDate, warrantyDays);
    }
}
