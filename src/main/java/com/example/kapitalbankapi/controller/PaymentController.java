package com.example.kapitalbankapi.controller;

import com.example.kapitalbankapi.payload.ApiResponse;
import com.example.kapitalbankapi.payload.PaymentDto;
import com.example.kapitalbankapi.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    //Get all payments by order id
    @GetMapping("{id}")
    public HttpEntity<?>getAllByOrderId(@PathVariable Integer id){
        ApiResponse apiResponse =paymentService.getAllByOrderId(id);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/Post/payment")
    public HttpEntity<?>addPayment(@RequestBody PaymentDto paymentDto){
        ApiResponse apiResponse = paymentService.addPayment(paymentDto);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }
    @GetMapping("/details/{id}")
    public HttpEntity<?>getDetails(@PathVariable Integer id){
        ApiResponse apiResponse = paymentService.getDetails(id);
        return ResponseEntity.ok(apiResponse);
    }



}
