package com.Zyara.Controller;

import com.Zyara.Model.CartItem;
import com.Zyara.Model.Address;
import com.Zyara.Model.Product;
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

    @PutMapping("/update-cart")
    public ResponseEntity<String> updateCartItem(@RequestBody CartItem cartDto) {
        return new ResponseEntity<>(cartService.updateCartItem(cartDto), HttpStatus.OK);
    }
    @DeleteMapping("/remove-from-cart")
    public ResponseEntity<String> removeItemFromCart(@RequestBody int id) {
        return new ResponseEntity<>(cartService.removeItemFromCart(id), HttpStatus.OK);
    }

    @GetMapping("/cart")
    public ResponseEntity<List<Product>> getCartItems() {
        return new ResponseEntity<>(cartService.getCartItems(), HttpStatus.OK);
    }


    @PostMapping("/add-address-to-cart")
    public ResponseEntity<String> addAddressToCart(@RequestBody Address address){
        return new ResponseEntity(cartService.addAddressToCart(address), HttpStatus.CREATED);
    }

    @PutMapping("/update-address-cart")
    public ResponseEntity<String> updateAddressInCart(@RequestBody Address address) {
        return new ResponseEntity<>(cartService.updateAddressInCart(address), HttpStatus.OK);
    }
    @DeleteMapping("/delete-address-from-cart")
    public ResponseEntity<String> DeleteAddressFromCart(@RequestBody int id) {
        return new ResponseEntity<>(cartService.deleteAddressFromCart(id), HttpStatus.OK);
    }

    @GetMapping("/get/all-addresses-in-cart")
    public ResponseEntity<List<Address>> getAllAddressesInCart() {
        return new ResponseEntity<>(cartService.getAllAddressesInCart(), HttpStatus.OK);
    }

}
