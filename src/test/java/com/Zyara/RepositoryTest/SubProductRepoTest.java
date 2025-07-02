package com.Zyara.RepositoryTest;

import com.Zyara.Model.Item;
import com.Zyara.Model.Product;
import com.Zyara.Model.ProductCategory;
import com.Zyara.Model.SubProduct;
import com.Zyara.Repository.ProductCategoryRepo;
import com.Zyara.Repository.SubProductItemRepo;
import com.Zyara.Repository.SubProductRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.parameters.P;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class SubProductRepoTest {
    @Autowired
    ProductCategoryRepo productCategoryRepo;
    @Autowired
    SubProductRepo subProductRepo;
    @Test
    public void findBySubProductIdTest(){
        ProductCategory productCategory=new ProductCategory();
        productCategory.setId("P1");
        productCategoryRepo.save(productCategory);
        SubProduct subProduct1 = new SubProduct();
        subProduct1.setId("1");
        subProduct1.setName("dress");
        subProduct1.setProduct(productCategory);
        subProductRepo.save(subProduct1);
        List<SubProduct> list=subProductRepo.findByProductId("P1");
        assertEquals("dress",list.get(0).getName());
        assertEquals(1,list.size());
    }
}
