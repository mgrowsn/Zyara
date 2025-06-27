package com.Zyara.ControllerTest;

import com.Zyara.Controller.CategoryController;
import com.Zyara.Dto.PopularItems;
import com.Zyara.Model.Category;
import com.Zyara.Model.Product;
import com.Zyara.Service.CategoryService;
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
public class CategoryControllerTest {
    @InjectMocks
    CategoryController categoryController;
    @Mock
    CategoryService categoryService;

    @Test
    public void getCategoryTest() {
        List<Category> list=List.of(new Category());
        when(categoryService.getCategory()).thenReturn(list);
        ResponseEntity<List<Category>> ans=categoryController.getCategory();
        assertEquals(list,ans.getBody());
        assertEquals(HttpStatus.OK,ans.getStatusCode());
    }
    @Test
    public void getSpecialityCategoryTest() {
        String categoryId="1";
        List<Product> list=List.of(new Product());
        when(categoryService.getSpecialityCategory(categoryId)).thenReturn(list);
        ResponseEntity<List<Product>> ans=categoryController.getSpecialityCategory(categoryId);
        assertEquals(list,ans.getBody());
        assertEquals(HttpStatus.OK,ans.getStatusCode());
    }
    @Test
    public void popularCategoriesTest() {
        Integer rating=4;
        List<PopularItems> list=List.of(new PopularItems());
        when(categoryService.popularCategories(rating)).thenReturn(list);
        ResponseEntity<List<PopularItems>> ans=categoryController.popularCategories(rating);
        assertEquals(list,ans.getBody());
        assertEquals(HttpStatus.OK,ans.getStatusCode());
    }
}
