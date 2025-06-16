package com.Zyara.Service;

import com.Zyara.Model.Product;
import com.Zyara.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {
    @Autowired
    ProductRepo productRepo;
    public List<Product> filterByBrand(String brand) {
        List<Product> list=productRepo.findAllByBrand(brand);
        return list;
    }
}
