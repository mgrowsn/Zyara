package com.Zyara.Repository;

import com.Zyara.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product,Integer>{
    List<Product> findAllByCategoryId(String categoryId);
    @Query(value = "SELECT * FROM speciality_category WHERE rating=:rating",nativeQuery = true)
    List<Product> findAllByRatingEqual(@Param("rating") int rating);
}
