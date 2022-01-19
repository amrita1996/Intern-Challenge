package com.logistics.shopifytechnicalchallengesubmission.inventory.controller.request;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

public class ItemRequest {
    private String description;
    private String sku;
    private Integer categoryId;
    private BigDecimal purchasedPrice;
    private Date manufacturedDate;
    private Integer warrantyDays;

    public ItemRequest() {
    }

    public ItemRequest(String description, String sku, Integer categoryId, BigDecimal purchasedPrice,
                       Date manufacturedDate, Integer warrantyDays) {
        this.description = description;
        this.sku = sku;
        this.categoryId = categoryId;
        this.purchasedPrice = purchasedPrice;
        this.manufacturedDate = manufacturedDate;
        this.warrantyDays = warrantyDays;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemRequest that = (ItemRequest) o;
        return Objects.equals(description, that.description) && Objects.equals(sku, that.sku)
                && Objects.equals(categoryId, that.categoryId) && Objects.equals(purchasedPrice, that.purchasedPrice)
                && Objects.equals(manufacturedDate, that.manufacturedDate)
                && Objects.equals(warrantyDays, that.warrantyDays);
    }
}
