package com.Zyara.Service;

import com.Zyara.Dto.ProductDto;
import com.Zyara.Model.Item;
import com.Zyara.Model.ProductCategory;
import com.Zyara.Repository.ProductCategoryRepo;
import com.Zyara.Model.SubProduct;
import com.Zyara.Repository.SubProductItemRepo;
import com.Zyara.Repository.SubProductRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductCategoryService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    ProductCategoryRepo productRepo;
    @Autowired
    SubProductRepo subProductRepo;

    @Autowired
    SubProductItemRepo subProductItemRepo;
    public List<ProductDto> getProducts() {
        List<ProductCategory> list=productRepo.findAll();
        List<ProductDto> products=list.stream()
                .map(product->modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
        return products;
    }

    public List<SubProduct> getSubProducts(String productId) {
        return subProductRepo.findByProductId(productId);
    }

    public List<Item> getSubProductsItems(String subProductId) {
        return subProductItemRepo.findBySubProductId(subProductId);
    }
}
