package com.libraryManagement.Category;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/categoryDetails")
public class CategoryController {
    @Autowired
    public CategoryService categoryService;

    @PostMapping(path = "/addCategories")
    public Category addCategory(@RequestBody  Category category){
        return categoryService.save(category);
    }


    @GetMapping(path = "/getCategory")
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

}
