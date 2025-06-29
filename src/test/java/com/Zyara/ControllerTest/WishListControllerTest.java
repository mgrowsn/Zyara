package com.Zyara.ControllerTest;

import com.Zyara.Controller.WishListController;
import com.Zyara.Dto.WishListDto;
import com.Zyara.Model.WishListItem;
import com.Zyara.Service.WishListService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WishListControllerTest {
    @InjectMocks
    WishListController wishListController;
    @Mock
    WishListService wishListService;
    @Test
    public void addItemToWishListTest() {
        WishListItem id=new WishListItem();
        when(wishListService.addItemToWishList(id)).thenReturn("Item added to wishlist");
        ResponseEntity<String> ans=wishListController.addItemToWishList(id);
        assertEquals("Item added to wishlist",ans.getBody());
        assertEquals(HttpStatus.CREATED,ans.getStatusCode());
    }
    @Test
    public void deleteItemFromWishListTest() {
        WishListItem id=new WishListItem();
        when(wishListService.deleteItemFromWishList(id)).thenReturn("Item removed from wishlist");
        ResponseEntity<String> ans=wishListController.deleteItemFromWishList(id);
        assertEquals("Item removed from wishlist",ans.getBody());
        assertEquals(HttpStatus.OK,ans.getStatusCode());
    }
    @Test
    public void getItemsOfWishListTest() {
        List<WishListDto> list=List.of(new WishListDto());
        when(wishListService.getItemsOfWishList()).thenReturn(list);
        ResponseEntity<List<WishListDto>> ans=wishListController.getItemsOfWishList();
        assertEquals(list,ans.getBody());
        assertEquals(HttpStatus.OK,ans.getStatusCode());
    }
}
