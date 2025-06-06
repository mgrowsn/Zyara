package com.Zyara;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;;
    public List<Category> getCategory(){
        List<Category> list= categoryRepository.findAll();
        return list;
    }
}
