package com.example.kapitalbankapi.service;

import com.example.kapitalbankapi.entity.Customer;
import com.example.kapitalbankapi.entity.Product;
import com.example.kapitalbankapi.payload.ApiResponse;
import com.example.kapitalbankapi.repository.CustomerRepository;
import com.example.kapitalbankapi.repository.DetailRepository;
import com.example.kapitalbankapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private DetailRepository detailRepository;


    public ApiResponse getProductInfoByCustomer(Integer id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (!optionalCustomer.isPresent()) return new ApiResponse("Customer does not exists",false);
        List<?> allInfo = productRepository.getAllInfo(id);
        return new ApiResponse("Success",true,allInfo);
    }

    public ApiResponse getProducts() {
        List<?> soldProductMoreThanTen = productRepository.getSoldProductMoreThanTen();
        return new ApiResponse("Success!",true,soldProductMoreThanTen);
    }

    public ApiResponse getAllByQuantity() {
        List<?> allByQuantity = productRepository.getAllByQuantity();
        return new ApiResponse("Success",true,allByQuantity);
    }

    public ApiResponse getTenExpensive() {
        List<?> tenExpensive = productRepository.getTenExpensive();
        return new ApiResponse("Success",true,tenExpensive);
    }

    public ApiResponse getAllProduct() {
        List<Product> all = productRepository.findAll();
        return new ApiResponse("Success",true,all);
    }

    public ApiResponse getDetailsByProductId(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent()) return new ApiResponse("Not Found",false);
        List<?> detailRepositoryAllByProduct_id = detailRepository.getAllByProduct_Id(id);
        return new ApiResponse("Success",true,detailRepositoryAllByProduct_id);
    }
}
