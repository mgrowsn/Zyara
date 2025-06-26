package com.Zyara.ServiceTest;

import com.Zyara.Dto.ProductDto;
import com.Zyara.Model.Item;
import com.Zyara.Model.ProductCategory;
import com.Zyara.Model.SubProduct;
import com.Zyara.Repository.ProductCategoryRepo;
import com.Zyara.Repository.SubProductItemRepo;
import com.Zyara.Repository.SubProductRepo;
import com.Zyara.Service.ProductCategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;


import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductCategoryServiceTest {
    @InjectMocks
    ProductCategoryService productCategoryService;
    @Mock
    ModelMapper modelMapper;
    @Mock
    ProductCategoryRepo productRepo;
    @Mock
    SubProductRepo subProductRepo;
    @Mock
    SubProductItemRepo subProductItemRepo;
    @Test
    public void getProductsTest() {
        ProductCategory productCategory=new ProductCategory();
        List<ProductCategory> productCategoryList=List.of(productCategory);
        when(productRepo.findAll()).thenReturn(productCategoryList);
        ProductDto productDto=new ProductDto();
        List<ProductDto> productDtoList=List.of(productDto);
        when(modelMapper.map(productCategory,ProductDto.class)).thenReturn(productDto);
        List<ProductDto> ans=productCategoryService.getProducts();
        assertEquals(productDtoList.size(),ans.size());
    }

    @Test
    public void getSubProductsTest() {
        when(subProductRepo.findByProductId("1")).thenReturn(List.of(new SubProduct()));
        List<SubProduct> list=productCategoryService.getSubProducts("1");
        assertEquals(1,list.size());
    }

    @Test
    public void getSubProductsItems() {
        when(subProductItemRepo.findBySubProductId("1")).thenReturn(List.of(new Item()));
        List<Item> list=productCategoryService.getSubProductsItems("1");
        assertEquals(1,list.size());
    }

}
