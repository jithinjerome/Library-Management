package com.libraryManagement.Category;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/categoryDetails")
public class CategoryController {
    @Autowired
    public CategoryService categoryService;

    //Add new category.
    @PostMapping(path = "/addCategories")
    public Category addCategory(@RequestBody  Category category){
        return categoryService.save(category);
    }


    //List of all categories.
    @GetMapping(path = "/getCategory")
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

}
