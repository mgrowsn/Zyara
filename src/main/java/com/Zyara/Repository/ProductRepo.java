package com.Zyara.Repository;

import com.Zyara.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product,String>{
    List<Product> findAllByCategoryId(String categoryId);
    @Query(value = "SELECT * FROM product WHERE rating<=:rating",nativeQuery = true)
    List<Product> findAllByRatingEqual(@Param("rating") int rating);

    @Query(value="SELECT * FROM product WHERE brand=:brand",nativeQuery = true)
    List<Product> findAllByBrand(@Param("brand") String brand);
}
