package com.example.kapitalbankapi.controller;


import com.example.kapitalbankapi.payload.ApiResponse;
import com.example.kapitalbankapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    //returns list of category
    @GetMapping("/list")
    private HttpEntity<?>getAllCategory(){
        ApiResponse apiResponse = categoryService.getAllCategory();
        return ResponseEntity.ok(apiResponse);
    }

    //returns category by product id
    @GetMapping("/product_id/{id}")
    private HttpEntity<?>getProductCategory(@PathVariable Integer id){
        ApiResponse apiResponse = categoryService.getProductCategory(id);
        return ResponseEntity.ok(apiResponse);
    }
}
