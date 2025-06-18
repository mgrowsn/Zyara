package com.Zyara.Service;

import com.Zyara.Dto.WishListDto;
import com.Zyara.Model.Product;
import com.Zyara.Model.WishListItem;
import com.Zyara.Repository.ProductRepo;
import com.Zyara.Repository.WishListRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishListService {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private WishListRepo wishListrepo;
    public String addItemToWishList(WishListItem id) {
        Product product=productRepo.findById(id.getId()).orElse(null);
        if(product == null) {
            return "Product not available";
        }
        if(product.getTotalCapacity() == 0) {
            return "Sorry, Product is out of stock";
        }
        wishListrepo.save(id);
        return "Item added to wishlist";
    }

    public String deleteItemFromWishList(WishListItem id) {
        Product product=productRepo.findById(id.getId()).orElse(null);
        if(product == null) {
            return "Product not found";
        }

        if(!wishListrepo.existsById(id.getId())) {
            return "Item not found in wishlist";
        }
        wishListrepo.delete(id);
        return "Item removed from wishlist";
    }

    public List<WishListDto> getItemsOfWishList() {
        List<WishListItem> wishListIds = wishListrepo.findAll();
        List<WishListDto> wishListItems = new ArrayList<>();
        for(WishListItem item:wishListIds){
            Product product=productRepo.findById(item.getId()).orElse(null);
            WishListDto wishListDto = new WishListDto();
            if(product!=null){
                wishListDto.setId(product.getId());
                wishListDto.setImage(product.getImage());
                wishListDto.setName(product.getName());
                wishListDto.setPrice(product.getPrice());
            }
            wishListItems.add(wishListDto);
        }
        return wishListItems;
    }
}
