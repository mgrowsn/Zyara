package com.Zyara.RepositoryTest;

import com.Zyara.Model.WishListItem;
import com.Zyara.Repository.WishListRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class WishListRepoTest {
    @Autowired
    WishListRepo wishListRepo;
    @Test
    public void saveWishListItemTest(){
        WishListItem wishListItem=new WishListItem("1");
        WishListItem saved=wishListRepo.save(wishListItem);
        assertEquals("1",saved.getId());
    }
    @Test
    public void deleteWishListItemTest(){
        WishListItem saved=wishListRepo.save(new WishListItem("2"));
        wishListRepo.delete(saved);
        assertEquals("2",saved.getId());
    }
    @Test
    public void existByIdTest(){
        WishListItem saved=wishListRepo.save(new WishListItem("3"));
        boolean itemExists=wishListRepo.existsById("3");
        boolean itemExists2=wishListRepo.existsById("4");
        assertEquals(false,itemExists2);
    }
}
