package com.Zyara.ControllerTest;

import com.Zyara.Dto.CartDto;
import com.Zyara.Model.Address;
import com.Zyara.Model.CartItem;
import com.Zyara.Service.CartService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import com.Zyara.Controller.CartController;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CartControllerTest {
    @Mock
    CartService cartService;
    @InjectMocks
    CartController cartController;

    @Test
    public void addItemToCartTest(){
        CartItem cartItem=new CartItem();
        when(cartService.addItemToCart(cartItem)).thenReturn("Item added to cart successfully");
        ResponseEntity<String> ans=cartController.addItemToCart(cartItem);
        assertEquals(HttpStatus.CREATED,ans.getStatusCode());
        assertEquals("Item added to cart successfully",ans.getBody());
    }

    @Test
    public void updateCartItemTest() {
        CartItem cartItem=new CartItem();
        when(cartService.updateCartItem(cartItem)).thenReturn("Cart item updated successfully");
        ResponseEntity<String> ans=cartController.updateCartItem(cartItem);
        assertEquals(HttpStatus.OK,ans.getStatusCode());
        assertEquals("Cart item updated successfully",ans.getBody());
    }

    @Test
    public void removeItemFromCartTest() {
        String id="1";
        when(cartService.removeItemFromCart(id)).thenReturn("Item removed from cart successfully");
        ResponseEntity<String> ans=cartController.removeItemFromCart(id);
        assertEquals("Item removed from cart successfully",ans.getBody());
        assertEquals(HttpStatus.OK,ans.getStatusCode());
    }

    @Test
    public void getCartItemsTest() {
        List<CartDto> list=List.of(new CartDto());
        when(cartService.getCartItems()).thenReturn(list);
        ResponseEntity<List<CartDto>> ans=cartController.getCartItems();
        assertEquals(list,ans.getBody());
        assertEquals(HttpStatus.OK,ans.getStatusCode());
    }

    @Test
    public void addAddressToCartTest(){
        Address address1=new Address();
        when(cartService.addAddressToCart(address1)).thenReturn("Address added successfully");
        ResponseEntity<String> ans=cartController.addAddressToCart(address1);
        assertEquals("Address added successfully",ans.getBody());
        assertEquals(HttpStatus.CREATED,ans.getStatusCode());
    }

    @Test
    public void updateAddressInCartTest() {
        Address address=new Address();
        when(cartService.updateAddressInCart(address)).thenReturn("Address updated successfully");
        ResponseEntity<String> ans=cartController.updateAddressInCart(address);
        assertEquals(HttpStatus.OK,ans.getStatusCode());
        assertEquals("Address updated successfully",ans.getBody());
    }
    @Test
    public void DeleteAddressFromCartTest() {
        int id=1;
        when(cartService.deleteAddressFromCart(id)).thenReturn("Address deleted successfully");
        ResponseEntity<String> ans=cartController.DeleteAddressFromCart(id);
        assertEquals(HttpStatus.OK,ans.getStatusCode());
        assertEquals("Address deleted successfully",ans.getBody());
    }

    @Test
    public void getAllAddressesInCartTest() {
        List<Address> list=List.of(new Address());
        when(cartService.getAllAddressesInCart()).thenReturn(list);
        ResponseEntity<List<Address>> ans=cartController.getAllAddressesInCart();
        assertEquals(list,ans.getBody());
        assertEquals(HttpStatus.OK,ans.getStatusCode());
    }

}
