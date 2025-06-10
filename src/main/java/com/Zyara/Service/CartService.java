package com.Zyara.Service;

import com.Zyara.Dto.CartDto;
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
        cartRepo.save(cartItem);
        return "Item added to cart successfully";
    }

    public String removeItemFromCart(int id) {
        if(!cartRepo.existsById(id)){
            return "Item not found in cart";
        }
        cartRepo.deleteById(id);
        return "Item removed from cart successfully";
    }

    public List<CartItem> getCartItems() {
        List<CartItem> list=cartRepo.findAll();
        return list;
    }

    public String updateCartItem(CartDto cartDto) {
        CartItem cartItem=cartRepo.findById(cartDto.getId()).orElse(null);
        if(cartItem == null) {
            return "Item not found in cart";
        }
        cartItem.setQuantity(cartDto.getQuantity());
        cartRepo.save(cartItem);
        return "Cart item updated successfully";
    }
}
