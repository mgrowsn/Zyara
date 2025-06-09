package com.Zyara.Controller;

import com.Zyara.Service.CategoryService;
import com.Zyara.Model.Category;
import com.Zyara.Dto.PopularItems;
import com.Zyara.Model.SpecialityCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@Slf4j
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @GetMapping("/all")
    public ResponseEntity<List<Category>> getCategory() {
        return new ResponseEntity<>(categoryService.getCategory(), HttpStatus.OK);
    }
    @GetMapping("/all/speciality-categories/{categoryId}")
    public ResponseEntity<List<SpecialityCategory>> getSpecialityCategory(@PathVariable String categoryId) {
        return new ResponseEntity<>(categoryService.getSpecialityCategory(categoryId), HttpStatus.OK);
    }
    @PostMapping("/popular-categories")
    public ResponseEntity<List<PopularItems>> popularCategories(@RequestBody Integer rating) {
        if(rating==null){
            rating =5;
        }
        log.info("rating is {}", rating);
        return new ResponseEntity<>(categoryService.popularCategories(rating), HttpStatus.OK);
    }
}
