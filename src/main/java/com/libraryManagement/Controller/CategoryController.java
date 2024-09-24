package com.libraryManagement.Controller;

import com.libraryManagement.Model.Category;
import com.libraryManagement.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "")
public class CategoryController {
    @Autowired
    public CategoryService categoryService;

    @PostMapping(path = "/addCategories")
    public Category addCategory(@RequestBody Category category){
        return categoryService.save(category);
    }


    @GetMapping(path = "/category")
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

}
