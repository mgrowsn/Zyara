package com.Zyara.Service;

import com.Zyara.Model.CartItem;
import com.Zyara.Repository.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    CartRepo cartRepo;
    public String addItemToCart(CartItem cartItem) {
        if(cartItem==null || cartItem.getQuantity() <= 0) {
            return "Invalid cart item";
        }
        if(cartRepo.existsById(cartItem.getId())){
            int quantity=cartRepo.findById(cartItem.getId()).get().getQuantity();
            cartItem.setQuantity(quantity+cartItem.getQuantity());
        }
        cartRepo.save(cartItem);
        return "Item added to cart successfully";
    }

    public String removeItemFromCart(CartItem cartItem) {
        if(cartItem == null) {
            return "Invalid cart item";
        }
        if(!cartRepo.existsById(cartItem.getId())) {
            return "Cart item not found";
        }
        cartRepo.delete(cartItem);
        return "Item removed from cart successfully";
    }

    public List<CartItem> getCartItems() {
        List<CartItem> list=cartRepo.findAll();
        return list;
    }
}
