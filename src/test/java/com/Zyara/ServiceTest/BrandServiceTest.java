package com.Zyara.ServiceTest;

import com.Zyara.Model.Product;
import com.Zyara.Repository.ProductRepo;
import com.Zyara.Service.BrandService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.parameters.P;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BrandServiceTest {
    @Mock
    ProductRepo productRepo;
    @InjectMocks
    BrandService brandService;

    private Product product1;
    private Product product2;
    private Product product3;

    @BeforeEach
    public void setUp() {
        product1 = new Product("1","dress","https://pictures.kartmax.in/live/inside/800x800/sites/aPfvUDpPwMn1ZadNKhP7/product-images/8905745653752/660/TTJ6008272_1.JPG", "Tokyo Talkies Women Navy Blue Solid Fit And Flare Dresses","clothing",849,4,4,"myntra",30);
        product2=new Product("2","dress","https://www.aachho.com/cdn/shop/files/46_67bfdfcf-59ac-4a2a-afb0-880473e51223_1080x.png?v=1694260202", "Salwar Suit Set","clothing",1000,4,6,"myntra",30);
        product3 = new Product("3","dress","https://pictures.kartmax.in/live/inside/800x800/sites/aPfvUDpPwMn1ZadNKhP7/product-images/8905745653752/660/TTJ6008272_1.JPG", "Tokyo Talkies Women Navy Blue Solid Fit And Flare Dresses","clothing",849,4,4,"zara",30);
    }

    @Test
    public void filterByBrandTest(){
        List<Product> mockList= Arrays.asList(product1,product2);
        when(productRepo.findAllByBrand("myntra")).thenReturn(mockList);
        List<Product> list=brandService.filterByBrand("myntra");
        assertEquals(2, list.size());
        assertEquals("myntra",list.get(0).getBrand());
        verify(productRepo, times(1)).findAllByBrand("myntra");
    }

    @Test
    public void emptyListWhenNoProductsForBrand() {
        when(productRepo.findAllByBrand("Apple")).thenReturn(Collections.emptyList());
        List<Product> list=brandService.filterByBrand("Apple");
        assertTrue(list.isEmpty());
        verify(productRepo, times(1)).findAllByBrand("Apple");
    }

    @Test
    public void emptyListWhenBrandIsNull() {
        when(productRepo.findAllByBrand(null)).thenReturn(Collections.emptyList());
        List<Product> list=brandService.filterByBrand(null);
        assertNotNull(list);
        assertTrue(list.isEmpty());
        verify(productRepo, times(1)).findAllByBrand(null);
    }

}