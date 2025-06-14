package com.Zyara.Controller;

import com.Zyara.Dto.WishListDto;
import com.Zyara.Model.WishListItem;
import com.Zyara.Service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WishListController {
    @Autowired
    WishListService wishListService;
    @PostMapping("/add-to-wishlist")
    public ResponseEntity<String> addItemToWishList(@RequestBody WishListItem  id) {
        return new ResponseEntity<>(wishListService.addItemToWishList(id), HttpStatus.CREATED);
    }
    @DeleteMapping("/delete-from-wishlist")
    public ResponseEntity<String> deleteItemFromWishList(@RequestBody WishListItem id) {
        return new ResponseEntity<>(wishListService.deleteItemFromWishList(id), HttpStatus.OK);
    }
    @GetMapping("/get-items-of-wishlist")
    public ResponseEntity<List<WishListDto>> getItemsOfWishList() {
        return new ResponseEntity<>(wishListService.getItemsOfWishList(), HttpStatus.OK);
    }
}
