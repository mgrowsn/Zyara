package com.Zyara.RepositoryTest;

import com.Zyara.Model.CartItem;
import com.Zyara.Model.Category;
import com.Zyara.Repository.CategoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class CategoryRepoTest {
    @Autowired
    CategoryRepository categoryRepository;
    @Test
    @DisplayName("Save Category Item")
    public void saveCategoryTest(){
        Category category=getSampleCategory(1L);
        Category saved=categoryRepository.save(category);
        assertEquals("Snacks",saved.getName());
    }

    @Test
    @DisplayName("Find By ID")
    public void findByIdTest(){
        Category category=getSampleCategory(3L);
        Category saved=categoryRepository.save(category);
        Optional<Category> category1=categoryRepository.findById(3L);
        assertTrue(category1.isPresent());
        assertEquals("Snacks",category1.get().getName());
    }

    @Test
    @DisplayName("Delete Category Item")
    public void deleteCategoryTest(){
        Category category=getSampleCategory(2L);
        Category saved=categoryRepository.save(category);
        categoryRepository.deleteById(2L);
        Optional<Category> res=categoryRepository.findById(2L);
        assertFalse(res.isPresent());
    }
    @Test
    @DisplayName("Find All Category Item")
    public void findAllCategoryTest(){
        Category category=getSampleCategory(5L);
        Category saved=categoryRepository.save(category);

        List<Category> res=categoryRepository.findAll();
        assertEquals(1,res.size());
    }
    private Category getSampleCategory(Long id) {
        return new Category(
                id,
                "image_url_" + id,
                id == 2L ? "Indian Dishes" : "Snacks",
                "Sample description for category " + id
        );
    }
}
