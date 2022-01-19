package com.logistics.shopifytechnicalchallengesubmission.inventory.repository;

import com.logistics.shopifytechnicalchallengesubmission.inventory.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
