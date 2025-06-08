package com.Zyara;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    SpecialityCategoryRepository specialityCategoryRepository;
    public List<Category> getCategory(){
        List<Category> list= categoryRepository.findAll();
        return list;
    }

    public List<SpecialityCategory> getSpecialityCategory(String categoryId) {
        List<SpecialityCategory> list=specialityCategoryRepository.findAllByCategoryId(categoryId);
        return list;
    }

    public List<SpecialityCategory> popularCategories(int rating) {
        List<SpecialityCategory> list=specialityCategoryRepository.findAllByRatingGreaterThanEqual(rating);
        return list;
    }
}
