package com.Zyara.Repository;

import com.Zyara.Model.SubProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SubProductRepo extends JpaRepository<SubProduct, String> {

    List<SubProduct> findByProductId(String productId);
}
