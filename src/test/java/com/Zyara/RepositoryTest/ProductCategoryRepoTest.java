package com.Zyara.RepositoryTest;

import com.Zyara.Model.ProductCategory;
import com.Zyara.Repository.ProductCategoryRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
public class ProductCategoryRepoTest {
    @Autowired
    ProductCategoryRepo productCategoryRepo;
    @BeforeEach
    public void setUp(){
        ProductCategory category = new ProductCategory();
        category.setId("PC01");
        category.setName("Electronics");
        category.setImage("electronics.jpg");
        productCategoryRepo.save(category);
    }
    @Test
    public void findAllTest(){
        List<ProductCategory> list=productCategoryRepo.findAll();
        assertEquals(1,list.size());
    }
    @Test
    public void findByIdTest(){
        Optional<ProductCategory> productCategory=productCategoryRepo.findById("PC01");
        assertEquals("Electronics",productCategory.get().getName());
    }
    @Test
    public void deleteByIdTest(){
        productCategoryRepo.deleteById("PC01");
        assertFalse(productCategoryRepo.findById("PC01").isPresent());
    }
}
