package com.Zyara.Service;

import com.Zyara.Dto.CartDto;
import com.Zyara.Model.Address;
import com.Zyara.Model.CartItem;
import com.Zyara.Model.Product;
import com.Zyara.Repository.AddressRepo;
import com.Zyara.Repository.CartRepo;
import com.Zyara.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    @Autowired
    ProductRepo productRepo;
    @Autowired
    CartRepo cartRepo;
    @Autowired
    AddressRepo addressRepo;
    public String addItemToCart(CartItem cartItem) {
        String productId=cartItem.getId();
        Product product=productRepo.findById(productId).orElse(null);

        if(product==null) {
            return "Product not available";
        }
        if(product.getTotalCapacity()==0){
            return "Sorry, Product is out of stock";
        }
        cartRepo.save(cartItem);
        return "Item added to cart successfully";
    }

    public String removeItemFromCart(String id) {
        if(!cartRepo.existsById(id)){
            return "Item not found in cart";
        }
        cartRepo.deleteById(id);
        return "Item removed from cart successfully";
    }

    public List<CartDto> getCartItems() {
        List<CartItem> list=cartRepo.findAll();
        List<CartDto> products=new ArrayList<>();
        for(CartItem item:list){
            Product product=productRepo.findById(item.getId()).orElse(null);
            if(product!=null){
                CartDto cartDto=new CartDto();
                cartDto.setId(item.getId());
                cartDto.setName(product.getName());
                cartDto.setPrice(product.getPrice());
                cartDto.setImage(product.getImage());
                cartDto.setDescription(product.getDescription());
                cartDto.setCategoryId(product.getCategoryId());
                cartDto.setRating(product.getRating());
                cartDto.setQuantity(item.getQuantity());
                products.add(cartDto);
            }
        }
        return products;
    }

    public String updateCartItem(CartItem cartItem) {
        CartItem item=cartRepo.findById(cartItem.getId()).orElse(null);
        Product product=productRepo.findById(cartItem.getId()).orElse(null);
        if(item == null) {
            return "Product not found in cart";
        }
        if(product!=null && cartItem.getQuantity()>product.getTotalCapacity()){
            return "Sorry, Your Quantity exceeds available stock";
        }
        item.setQuantity(cartItem.getQuantity());
        cartRepo.save(item);
        return "Cart item updated successfully";
    }

    public String addAddressToCart(Address address) {
        if(!validationToAddAddress(address).equals("Valid Address")){
            return validationToAddAddress(address);
        }
        addressRepo.save(address);
        return "Address added successfully";
    }

    private String validationToAddAddress(Address address) {
        if(address.getAddress() == null || address.getCity() == null || address.getCountry() == null ||
                address.getPhoneNumber() == null || address.getPincode() == null || address.getState() == null) {
            return "Please provide all required fields for the address";
        }
        if(address.getPhoneNumber().length() != 10) {
            return "Phone number must be 10 digits long";
        }
        if(address.getPincode().length() != 6) {
            return "Pincode must be 6 digits long";
        }
        return "Valid Address";
    }

    public String updateAddressInCart(Address address) {
        if(!validationToAddAddress(address).equals("Valid Address")){
            return validationToAddAddress(address);
        }
        Address existingAddress=addressRepo.findById(address.getId()).orElse(null);
        if(existingAddress==null){
            return "Address not found";
        }
        existingAddress.setAddress(address.getAddress());
        existingAddress.setAddress(address.getCity());
        existingAddress.setAddress(address.getCountry());
        existingAddress.setAddress(address.getPhoneNumber());
        existingAddress.setAddress(address.getPincode());
        existingAddress.setAddress(address.getState());
        existingAddress.setAddress(address.getLandmark());
        addressRepo.save(existingAddress);
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
