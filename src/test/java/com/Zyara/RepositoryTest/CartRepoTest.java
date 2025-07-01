package com.Zyara.RepositoryTest;

import com.Zyara.Model.Address;
import com.Zyara.Model.CartItem;
import com.Zyara.Repository.CartRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CartRepoTest {
    @Autowired
    CartRepo cartRepo;
    @Test
    @DisplayName("Save Cart Item")
    public void saveCartItemTest(){
        CartItem cartItem=new CartItem("1",2);
        CartItem saved=cartRepo.save(cartItem);
        assertEquals("1",saved.getId());
        assertEquals(2,saved.getQuantity());
    }

    @Test
    @DisplayName("Find By ID")
    public void findByIdTest(){
        CartItem cartItem=new CartItem("2",2);
        CartItem saved=cartRepo.save(cartItem);
        Optional<CartItem> address1=cartRepo.findById("2");
        assertTrue(address1.isPresent());
        assertEquals(2,cartItem.getQuantity());
    }

    @Test
    @DisplayName("Delete Cart Item")
    public void deleteCartItemTest(){
        CartItem cartItem=new CartItem("3",4);
        CartItem saved=cartRepo.save(cartItem);
        cartRepo.deleteById("3");
        Optional<CartItem> res=cartRepo.findById("3");
        assertFalse(res.isPresent());
    }
    @Test
    @DisplayName("Find All Cart Item")
    public void findAllCartItemTest(){
        CartItem saved1=cartRepo.save(new CartItem("4",6));
        CartItem saved2=cartRepo.save(new CartItem("5",8));

        List<CartItem> res=cartRepo.findAll();
        assertEquals(2,res.size());
    }
}
