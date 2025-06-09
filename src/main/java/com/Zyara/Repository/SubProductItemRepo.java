package com.Zyara.Repository;

import com.Zyara.Model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SubProductItemRepo extends JpaRepository<Item, String> {
    List<Item> findBySubProductId(String subProductId);
}