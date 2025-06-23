package com.Zyara.ServiceTest;

import com.Zyara.Dto.CartDto;
import com.Zyara.Model.Address;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
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

    @Test
    public void removeItemFromCartTest() {
        when(cartRepo.existsById("1")).thenReturn(true);
        String result=cartService.removeItemFromCart("1");
//        doNothing().when(cartRepo).deleteById("1");
        assertEquals("Item removed from cart successfully",result);
    }
    @Test
    public void removeItemFromCartWhenNotExists() {
        when(cartRepo.existsById("1")).thenReturn(false);
        String result=cartService.removeItemFromCart("1");
        assertEquals("Item not found in cart",result);
    }

    @Test
    public void updateCartItemTest() {
        CartItem item=new CartItem();
        item.setId("1");
        item.setQuantity(5);
        CartItem cartItem=new CartItem();
        Product product=new Product();
        product.setTotalCapacity(10);
        when(cartRepo.findById("1")).thenReturn(Optional.of(cartItem));
        when(productRepo.findById("1")).thenReturn(Optional.of(product));
        when(cartRepo.save(cartItem)).thenReturn(cartItem);
        String result=cartService.updateCartItem(item);
        assertEquals("Cart item updated successfully",result);
    }

    @Test
    public void updateCartItemButItemNotFoundInCart() {
        CartItem item=new CartItem();
        item.setId("1");
        when(cartRepo.findById("1")).thenReturn(Optional.empty());
        String result=cartService.updateCartItem(item);
        assertEquals("Product not found in cart",result);
    }

    @Test
    public void updateCartItemButQuantityExceeds() {
        CartItem item=new CartItem();
        item.setId("1");
        item.setQuantity(15);
        CartItem cartItem=new CartItem();
        Product product=new Product();
        product.setTotalCapacity(10);
        when(cartRepo.findById("1")).thenReturn(Optional.of(cartItem));
        when(productRepo.findById("1")).thenReturn(Optional.of(product));
        String result=cartService.updateCartItem(item);
        assertEquals("Sorry, Your Quantity exceeds available stock",result);
    }


    public void getCartItemsTest() {
        CartItem cartItem1=new CartItem("1",3);
        CartItem cartItem2=new CartItem("2",4);

        Product product1 = new Product();
        product1.setName("Product 1");
        product1.setPrice(100.0);
        product1.setImage("image1.jpg");
        product1.setDescription("Description 1");
        product1.setCategoryId("Cat1");
        product1.setRating(4);

        Product product2 = new Product();
        product2.setName("Product 2");
        product2.setPrice(200.0);
        product2.setImage("image2.jpg");
        product2.setDescription("Description 2");
        product2.setCategoryId("Cat2");
        product2.setRating(4);

        List<CartItem> cartItemList=List.of(cartItem1,cartItem2);
        when(cartRepo.findAll()).thenReturn(cartItemList);
        when(productRepo.findById("1")).thenReturn(Optional.of(product1));
        when(productRepo.findById("2")).thenReturn(Optional.of(product2));
        List<CartDto> list=cartService.getCartItems();
        assertEquals(2,list.size());
        CartDto dto1=list.get(0);
        assertEquals("1",dto1.getId());
        assertEquals("Product 1",dto1.getName());
        assertEquals(2,dto1.getQuantity());
        CartDto dto2=list.get(0);
        assertEquals("2",dto2.getId());
        assertEquals("Product 2",dto2.getName());
        assertEquals(1,dto1.getQuantity());
    }

    @Test
    public void addAddressToCartTest() {
        Address address = new Address();
        address.setAddress("Street");
        address.setCity("City");
        address.setCountry("Country");
        address.setPhoneNumber("1234567890");
        address.setPincode("123456");
        address.setState("State");
        address.setLandmark("Near hemkunt school");
        when(addressRepo.save(address)).thenReturn(address);
        String result=cartService.addAddressToCart(address);
        assertEquals("Address added successfully",result);
    }

    @Test
    public void invalidAddressWhenAddToCart() {
        Address address = new Address();
        String result=cartService.addAddressToCart(address);
        assertEquals("Please provide all required fields for the address",result);
    }
    @Test
    public void deleteAddressFromCartTest() {
        Address address=new Address();
        when(addressRepo.findById(1)).thenReturn(Optional.of(address));
        doNothing().when(addressRepo).delete(address);
        String result=cartService.deleteAddressFromCart(1);
        assertEquals("Address deleted successfully",result);
    }

    @Test
    public void deleteAddressFromCartButAddressNotFound() {
        Address address=new Address();
        address.setId(1);
        address.setAddress("123 Street");
        address.setCity("Hyderabad");
        address.setCountry("India");
        address.setPhoneNumber("1234567890");
        address.setPincode("500001");
        address.setState("Telangana");
        address.setLandmark("Near Lake");
        when(addressRepo.findById(1)).thenReturn(Optional.empty());
        String result=cartService.deleteAddressFromCart(1);
        assertEquals("Address not found",result);
    }
    @Test
    public void updateAddressInCartTest() {
        Address address=new Address();
        address.setId(1);
        address.setAddress("123 Street");
        address.setCity("Hyderabad");
        address.setCountry("India");
        address.setPhoneNumber("3456789443");
        address.setPincode("234568");
        address.setState("Telangana");
        address.setLandmark("Near Lake");
        when(addressRepo.findById(1)).thenReturn(Optional.of(address));
        when(addressRepo.save(address)).thenReturn(address);
        String result=cartService.updateAddressInCart(address);
        assertEquals("Address updated successfully",result);
    }

    @Test
    public void updateAddressButInvalidDetails() {
        Address address=new Address();
        address.setId(1);
        String result=cartService.updateAddressInCart(address);
        assertEquals("Please provide all required fields for the address",result);
    }

    @Test
    public void updateButAddressNotFound() {
        Address address=new Address();
        address.setId(1);
        address.setAddress("123 Street");
        address.setCity("Hyderabad");
        address.setCountry("India");
        address.setPhoneNumber("1234567890");
        address.setPincode("500001");
        address.setState("Telangana");
        address.setLandmark("Near Lake");

        when(addressRepo.findById(1)).thenReturn(Optional.empty());
        String result=cartService.updateAddressInCart(address);
        assertEquals("Address not found",result);
    }
    @Test
    public void getAllAddressesInCartTest() {
        Address address=new Address();
        address.setId(1);
        address.setAddress("123 Street");
        address.setCity("Hyderabad");
        address.setCountry("India");
        address.setPhoneNumber("1234567890");
        address.setPincode("500001");
        address.setState("Telangana");
        address.setLandmark("Near Lake");
        List<Address> addresses=List.of(address);
        when(addressRepo.findAll()).thenReturn(addresses);
        List<Address> list=cartService.getAllAddressesInCart();
        Address myAdd=list.get(0);
        assertEquals(1,myAdd.getId());
        assertEquals("123 Street",myAdd.getAddress());
    }
    public List<Address> getAllAddressesInCart() {
        List<Address> addresses=addressRepo.findAll();
        return addresses;
    }
}
