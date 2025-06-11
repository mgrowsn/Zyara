package com.Zyara.Service;

import com.Zyara.Model.Address;
import com.Zyara.Dto.CartDto;
import com.Zyara.Model.CartItem;
import com.Zyara.Repository.AddressRepo;
import com.Zyara.Repository.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    CartRepo cartRepo;
    @Autowired
    AddressRepo addressRepo;
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

    public String addAddressToCart(Address address) {
        if(address==null || address.getAddress().isEmpty()) {
            return "Invalid address";
        }
        addressRepo.save(address);
        return "Address added successfully";
    }

    public String updateAddressInCart(Address address) {
        if(address == null || address.getAddress() == null) {
            return "Please provide valid address to update";
        }
        Address existingAddress=addressRepo.findById(address.getId()).orElse(null);
        if(existingAddress==null){
            return "Address not found";
        }
        existingAddress.setAddress(address.getAddress());
        addressRepo.save(address);
        return "Address updated successfully";
    }

    public String deleteAddressFromCart(int id) {
        Address existingAddress=addressRepo.findById(id).orElse(null);
        if(existingAddress==null){
            return "Address not found";
        }
        addressRepo.delete(existingAddress);
        return "Address deleted successfully";
    }

    public List<Address> getAllAddressesInCart() {
        List<Address> addresses=addressRepo.findAll();
        return addresses;
    }
}
