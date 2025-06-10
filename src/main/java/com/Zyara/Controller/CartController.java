package com.Zyara.Controller;

import com.Zyara.Model.CartItem;
import com.Zyara.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CartController {
    @Autowired
    CartService cartService;
    @PostMapping("/add-to-cart")
    public ResponseEntity<String> addItemToCart(@RequestBody CartItem cartItem){
        return new ResponseEntity(cartService.addItemToCart(cartItem), HttpStatus.CREATED);
    }

    @DeleteMapping("/remove-from-cart")
    public ResponseEntity<String> removeItemFromCart(@RequestBody CartItem cartItem) {
        return new ResponseEntity<>(cartService.removeItemFromCart(cartItem), HttpStatus.OK);
    }

    @GetMapping("/cart")
    public ResponseEntity<List<CartItem>> getCartItems() {
        List<CartItem> list=cartService.getCartItems();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
