package com.Zyara.RepositoryTest;

import com.Zyara.Model.Product;
import com.Zyara.Repository.ProductRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class ProductRepoTest {
    @Autowired
    ProductRepo productRepo;
    @BeforeEach
    public void setUp(){
        Product p1 = new Product("1", "Phone", "image1.jpg", "Smartphone", "electronics", 10000, 5, 100, "Samsung", 10);
        Product p2 = new Product("2", "TV", "image2.jpg", "Smart TV", "electronics", 30000, 4, 50, "Sony", 15);
        Product p3 = new Product("3", "Shoes", "image3.jpg", "Running Shoes", "fashion", 2000, 5, 200, "Nike", 5);

        productRepo.saveAll(List.of(p1, p2, p3));
    }
    @Test
    public void findAllByCategoryIdTest(){
        List<Product> list=productRepo.findAllByCategoryId("electronics");
        assertEquals(2,list.size());
        assertEquals("Phone",list.get(0).getName());
    }
    @Test
    public void findAllByRatingEqualTest(){
        List<Product> list=productRepo.findAllByRatingEqual(5);
        assertEquals(3,list.size());
    }

    @Test
    public void findAllByBrandTest(){
        List<Product> list=productRepo.findAllByBrand("Samsung");
        assertEquals(1,list.size());
        assertEquals("Phone",list.get(0).getName());
    }
}
