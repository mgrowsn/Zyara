package com.Zyara.Service;

import com.Zyara.Model.Category;
import com.Zyara.Dto.PopularItems;
import com.Zyara.Repository.CategoryRepository;
import com.Zyara.Model.SpecialityCategory;
import com.Zyara.Repository.SpecialityCategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ModelMapper modelMapper;
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

    public List<PopularItems> popularCategories(int rating) {
        if (rating < 0 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 0 and 5");
        }
        List<SpecialityCategory> list=specialityCategoryRepository.findAllByRatingEqual(rating);
        List<PopularItems> popularItems=list.stream()
                        .map(specialityCategory -> modelMapper.map(specialityCategory, PopularItems.class))
                                .collect(Collectors.toList());
        return popularItems;
    }
}
