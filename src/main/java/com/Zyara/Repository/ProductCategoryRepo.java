package com.Zyara.Repository;

import com.Zyara.Model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ProductCategoryRepo extends JpaRepository<ProductCategory, String> {
}


