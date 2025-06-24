package com.Zyara.ServiceTest;

import com.Zyara.Dto.PopularItems;
import com.Zyara.Model.Category;
import com.Zyara.Model.Product;
import com.Zyara.Repository.CategoryRepository;
import com.Zyara.Repository.ProductRepo;
import com.Zyara.Service.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.parameters.P;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {
    @InjectMocks
    CategoryService categoryService;
    @Mock
    ProductRepo productRepo;
    @Mock
    CategoryRepository categoryRepository;
    @Mock
    ModelMapper modelMapper;

    @Test
    public void getCategoryTest(){
        Category category1=new Category();
        Category category2=new Category();
        List<Category> list=List.of(category1,category2);
        when(categoryRepository.findAll()).thenReturn(list);
        List<Category> ans= categoryService.getCategory();
        assertEquals(ans.size(),list.size());
    }

    @Test
    public void getSpecialityCategoryTest() {
        Product product1=new Product();
        Product product2=new Product();
        List<Product> list=List.of(product1,product2);
        when(productRepo.findAllByCategoryId("electronics")).thenReturn(list);
        List<Product> ans= categoryService.getSpecialityCategory("electronics");
        assertEquals(ans.size(),list.size());
    }

    @Test
    public void popularCategories() {
        Product product1=new Product();
        List<Product> list=List.of(product1);
        PopularItems popularItems=new PopularItems();
        when(productRepo.findAllByRatingEqual(4)).thenReturn(list);
        when(modelMapper.map(product1, PopularItems.class)).thenReturn(popularItems);
        List<PopularItems> popularItemsList=categoryService.popularCategories(4);
        assertEquals(popularItemsList.size(),list.size());
    }

    @Test
    public void popularCategoriesButInvalidRating() {
        IllegalArgumentException exception=assertThrows(IllegalArgumentException.class,()->{
            categoryService.popularCategories(7);
        });
        assertEquals("Rating must be between 0 and 5",exception.getMessage());
    }

}
