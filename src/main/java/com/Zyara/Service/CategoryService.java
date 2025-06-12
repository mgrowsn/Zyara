package com.Zyara.Service;

import com.Zyara.Model.Category;
import com.Zyara.Dto.PopularItems;
import com.Zyara.Model.Product;
import com.Zyara.Repository.CategoryRepository;
import com.Zyara.Repository.ProductRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    ProductRepo productRepo;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ModelMapper modelMapper;
    public List<Category> getCategory(){
        List<Category> list= categoryRepository.findAll();
        return list;
    }

    public List<Product> getSpecialityCategory(String categoryId) {
        List<Product> list=productRepo.findAllByCategoryId(categoryId);
        return list;
    }

    public List<PopularItems> popularCategories(int rating) {
        if (rating < 0 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 0 and 5");
        }
        List<PopularItems> popularItems=productRepo.findAllByRatingEqual(rating)
                .stream()
                .map(product->modelMapper.map(product, PopularItems.class))
                .collect(Collectors.toList());
        return popularItems;
    }
}
