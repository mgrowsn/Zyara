package com.Zyara.ServiceTest;

import com.Zyara.Dto.WishListDto;
import com.Zyara.Model.Product;
import com.Zyara.Model.WishListItem;
import com.Zyara.Repository.ProductRepo;
import com.Zyara.Repository.WishListRepo;
import com.Zyara.Service.WishListService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WishListServiceTest{
    @Mock
    ProductRepo productRepo;
    @Mock
    WishListRepo wishListrepo;
    @InjectMocks
    WishListService wishListService;
    @Test
    public void addItemToWishListTest() {
        WishListItem wishListItem=new WishListItem();
        wishListItem.setId("1");
        Product product=new Product();
        product.setTotalCapacity(10);
        when(productRepo.findById("1")).thenReturn(Optional.of(product));
        when(wishListrepo.save(wishListItem)).thenReturn(wishListItem);
        String res= wishListService.addItemToWishList(wishListItem);
        assertEquals("Item added to wishlist",res);
    }

    @Test
    public void addItemToWishListBuProductNotFound() {
        WishListItem wishListItem=new WishListItem();
        wishListItem.setId("1");
        when(productRepo.findById("1")).thenReturn(Optional.empty());
        String res= wishListService.addItemToWishList(wishListItem);
        assertEquals("Product not available",res);
    }

    @Test
    public void addItemToWishListButProductOutOfStock() {
        WishListItem wishListItem=new WishListItem();
        wishListItem.setId("1");
        Product product=new Product();
        product.setTotalCapacity(0);
        when(productRepo.findById("1")).thenReturn(Optional.of(product));
        String res= wishListService.addItemToWishList(wishListItem);
        assertEquals("Sorry, Product is out of stock",res);
    }

    @Test
    public void deleteItemFromWishListTest() {
        WishListItem wishListItem=new WishListItem();
        wishListItem.setId("1");
        Product product=new Product();
        when(productRepo.findById("1")).thenReturn(Optional.of(product));
        when(wishListrepo.existsById("1")).thenReturn(true);
        doNothing().when(wishListrepo).delete(wishListItem);
        String res=wishListService.deleteItemFromWishList(wishListItem);
        assertEquals("Item removed from wishlist",res);
    }
    @Test
    public void deleteItemFromWishListButProductNotFound() {
        WishListItem wishListItem=new WishListItem();
        wishListItem.setId("1");
        when(productRepo.findById("1")).thenReturn(Optional.empty());
        String res=wishListService.deleteItemFromWishList(wishListItem);
        assertEquals("Product not found",res);
    }

    public void deleteButItemNotFoundInWishList() {
        WishListItem wishListItem=new WishListItem();
        wishListItem.setId("1");
        Product product=new Product();
        when(productRepo.findById("1")).thenReturn(Optional.of(product));
        when(wishListrepo.existsById("1")).thenReturn(false);
        String res=wishListService.deleteItemFromWishList(wishListItem);
        assertEquals("IItem not found in wishlist",res);
    }

    @Test
    public void getItemsOfWishListTest() {
        WishListItem wishListItem=new WishListItem();
        wishListItem.setId("1");
        List<WishListItem> list=List.of(wishListItem);
        when(wishListrepo.findAll()).thenReturn(list);
        Product product=new Product();
        product.setId("1");
        product.setImage("image2.jpg");
        product.setName("dress");
        product.setPrice(6000);
        when(productRepo.findById("1")).thenReturn(Optional.of(product));
        List<WishListDto> wishListDtos=wishListService.getItemsOfWishList();
        WishListDto wishListDto1=wishListDtos.get(0);
        assertEquals("1",wishListDto1.getId());
        assertEquals("image2.jpg",wishListDto1.getImage());
        assertEquals("dress",wishListDto1.getName());
        assertEquals(6000,wishListDto1.getPrice());
    }

}
