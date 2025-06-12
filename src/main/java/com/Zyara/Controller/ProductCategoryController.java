package com.Zyara.Controller;

import com.Zyara.Model.Item;
import com.Zyara.Dto.ProductDto;
import com.Zyara.Service.ProductCategoryService;
import com.Zyara.Model.SubProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ProductCategoryController {
    @Autowired
    ProductCategoryService productService;
    @GetMapping("/all/products")
    public ResponseEntity<List<ProductDto>> getProducts(){
        return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
    }
    @GetMapping("/all/products/{productId}/subproducts")
    public ResponseEntity<List<SubProduct>> getSubProducts(@PathVariable String productId){
        return new ResponseEntity<>(productService.getSubProducts(productId), HttpStatus.OK);
    }
    @GetMapping("/subproducts/{subProductId}/items")
    public ResponseEntity<List<Item>> getSubProductsItems(@PathVariable String subProductId){
        return new ResponseEntity<>(productService.getSubProductsItems(subProductId), HttpStatus.OK);
    }
}
