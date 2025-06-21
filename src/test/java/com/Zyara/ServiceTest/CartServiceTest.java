package com.Zyara.ServiceTest;

import com.Zyara.Model.CartItem;
import com.Zyara.Model.Product;
import com.Zyara.Repository.AddressRepo;
import com.Zyara.Repository.CartRepo;
import com.Zyara.Repository.ProductRepo;
import com.Zyara.Service.CartService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {
    @Mock
    ProductRepo productRepo;
    @Mock
    CartRepo cartRepo;
    @Mock
    AddressRepo addressRepo;
    @InjectMocks
    CartService cartService;

    @Test
    public void addItemToCartTest() {
        CartItem item=new CartItem();
        item.setId("1");
        Product product=new Product();
        product.setTotalCapacity(5);
        when(productRepo.findById("1")).thenReturn(Optional.of(product));
        when(cartRepo.save(item)).thenReturn(item);
        String result=cartService.addItemToCart(item);
        assertEquals("Item added to cart successfully", result);
    }
    @Test
    public void addItemToCartButProductNotFound() {
        CartItem item=new CartItem();
        item.setId("1");
        Product product=new Product();
        when(productRepo.findById("1")).thenReturn(Optional.empty());
        String result=cartService.addItemToCart(item);
        assertEquals("Product not available", result);
    }
    @Test
    public void addItemToCartWhenProductOutOfStock() {
        CartItem item=new CartItem();
        item.setId("1");
        Product product=new Product();
        product.setTotalCapacity(0);
        when(productRepo.findById("1")).thenReturn(Optional.of(product));
        String result=cartService.addItemToCart(item);
        assertEquals("Sorry, Product is out of stock", result);
    }
}
