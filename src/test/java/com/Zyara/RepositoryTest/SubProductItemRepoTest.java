package com.Zyara.RepositoryTest;

import com.Zyara.Model.Item;
import com.Zyara.Model.SubProduct;
import com.Zyara.Repository.SubProductItemRepo;
import com.Zyara.Repository.SubProductRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class SubProductItemRepoTest {
    @Autowired
    SubProductItemRepo subProductItemRepo;
    @Autowired
    SubProductRepo subProductRepo;
    @Test
    public void findBySubProductIdTest(){
        SubProduct subProduct=new SubProduct();
        subProduct.setId("P1");
        subProductRepo.save(subProduct);
        Item item1 = new Item();
        item1.setId("1");
        item1.setName("dress");
        item1.setSubProduct(subProduct);
        subProductItemRepo.save(item1);
        List<Item> list=subProductItemRepo.findBySubProductId("P1");
        assertEquals("dress",list.get(0).getName());
        assertEquals(1,list.size());
    }
}