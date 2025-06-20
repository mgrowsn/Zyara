package com.Zyara.Controller;

import com.Zyara.Model.Product;
import com.Zyara.Service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class BrandController {
    @Autowired
    BrandService brandService;
    @PostMapping("/filter-by-brand")
    public ResponseEntity<List<Product>> filterByBrand(@RequestBody String brand) {
        return new ResponseEntity<>(brandService.filterByBrand(brand), HttpStatus.OK);
    }
}
