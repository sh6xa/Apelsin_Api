package com.example.kapitalbankapi.service;

import com.example.kapitalbankapi.entity.Category;
import com.example.kapitalbankapi.entity.Product;
import com.example.kapitalbankapi.payload.ApiResponse;
import com.example.kapitalbankapi.repository.CategoryRepository;
import com.example.kapitalbankapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

    public ApiResponse getAllCategory() {
        List<Category> categoryList = categoryRepository.findAll();
        return new ApiResponse("Success",true,categoryList);
    }

    public ApiResponse getProductCategory(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent()) return new ApiResponse("Not Found",false);
        Optional<?> optional = categoryRepository.findByProductId(id);
        return new ApiResponse("Success",true,optional.get());
    }
}
