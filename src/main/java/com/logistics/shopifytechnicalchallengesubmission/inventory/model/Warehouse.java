package com.logistics.shopifytechnicalchallengesubmission.inventory.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Warehouse {
    @Id
    private Long warehouseId;
    private String warehouseCode;
    private String name;
}
