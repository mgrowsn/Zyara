package com.Zyara;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
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
    public ResponseEntity<List<SpecialityCategory>> popularCategories(@RequestParam(value = "rating", defaultValue = "4") int rating) {
        return new ResponseEntity<>(categoryService.popularCategories(rating), HttpStatus.OK);
    }
}
