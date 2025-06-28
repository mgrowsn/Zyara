package com.Zyara.ControllerTest;

import com.Zyara.Controller.ProductCategoryController;
import com.Zyara.Dto.ProductDto;
import com.Zyara.Model.Item;
import com.Zyara.Model.SubProduct;
import com.Zyara.Service.ProductCategoryService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductCategoryControllerTest {
    @InjectMocks
    ProductCategoryController productCategoryController;
    @Mock
    ProductCategoryService productService;

    @Test
    public void getProductsTest(){
        List<ProductDto> list=List.of(new ProductDto());
        when(productService.getProducts()).thenReturn(list);
        ResponseEntity<List<ProductDto>> ans=productCategoryController.getProducts();
        assertEquals(list,ans.getBody());
        assertEquals(HttpStatus.OK,ans.getStatusCode());
    }
    @Test
    public void getSubProductsTest(){
        String productId="1";
        List<SubProduct> list=List.of(new SubProduct());
        when(productService.getSubProducts("1")).thenReturn(list);
        ResponseEntity<List<SubProduct>> ans=productCategoryController.getSubProducts(productId);
        assertEquals(list,ans.getBody());
        assertEquals(HttpStatus.OK,ans.getStatusCode());
    }
    @Test
    public void getSubProductsItemsTest(){
        String subProductId="1";
        List<Item> list=List.of(new Item());
        when(productService.getSubProductsItems("1")).thenReturn(list);
        ResponseEntity<List<Item>> ans=productCategoryController.getSubProductsItems(subProductId);
        assertEquals(list,ans.getBody());
        assertEquals(HttpStatus.OK,ans.getStatusCode());
    }
}
