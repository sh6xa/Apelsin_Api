package com.example.kapitalbankapi.controller;

import com.example.kapitalbankapi.payload.ApiResponse;
import com.example.kapitalbankapi.service.OrderService;
import com.sun.tools.javac.util.DefinedBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    //Get all orders by Customer id
    @GetMapping("{id}")
    public HttpEntity<?>getByCustomer(@PathVariable Integer id){
     ApiResponse apiResponse = orderService.getByCustomer(id);
     return ResponseEntity.ok(apiResponse);
    }

    //Get all orders by date between
    @GetMapping("/by_date")
    public HttpEntity<?>getAllOrderByDate(@RequestParam Date dateFrom,@RequestParam Date dateTo){
        ApiResponse apiResponse = orderService.getAllOrderByDate(dateFrom,dateTo);
        return ResponseEntity.ok(apiResponse);
    }

    //Get Last orders by customer name
    @GetMapping("/customers_last_orders")
    public HttpEntity<?> getLastOrders(){
        ApiResponse apiResponse = orderService.getLastOrders();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/orders_without_details")
    public HttpEntity<?> getOrdersWithoutDetails(){
        ApiResponse apiResponse = orderService.getOrdersWithoutDetails();
        return ResponseEntity.ok(apiResponse);
    }
    //get total number of orders each country in 2021
    @GetMapping("/number_of_products_in_year")
    public HttpEntity<?> getNumberOfProducts(){
        ApiResponse apiResponse = orderService.getNumberOfProductsInYear();
        return ResponseEntity.ok(apiResponse);
    }
    //get Order details by order id
    @GetMapping("/details/{id}")
    public HttpEntity<?> getOrderDetails(@PathVariable Integer id){
        ApiResponse apiResponse = orderService.getOrderDetails(id);
        return ResponseEntity.ok(apiResponse);
    }



}
