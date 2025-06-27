package com.Zyara.ControllerTest;

import com.Zyara.Controller.BrandController;
import com.Zyara.Model.Product;
import com.Zyara.Service.BrandService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class BrandControllerTest {
    @Mock
    BrandService brandService;
    @InjectMocks
    BrandController brandController;

    @Test
    public void filterByBrandTest() {
        String brand="Nike";
        Product product=new Product();
        List<Product> list=List.of(product);
        when(brandService.filterByBrand("Nike")).thenReturn(list);
        ResponseEntity<List<Product>> ans=brandController.filterByBrand(brand);
        assertEquals(HttpStatus.OK,ans.getStatusCode());
        assertEquals(list,ans.getBody());
    }
}
