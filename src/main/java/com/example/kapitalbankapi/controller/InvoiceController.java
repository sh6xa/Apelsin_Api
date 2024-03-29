package com.example.kapitalbankapi.controller;

import com.example.kapitalbankapi.payload.ApiResponse;
import com.example.kapitalbankapi.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {
    @Autowired
    InvoiceService invoiceService;

    @GetMapping("/expired_invoices")
    public HttpEntity<?> getInvoiceIssued(){
        ApiResponse apiResponse =invoiceService.getInvoiceIssued();
        return  ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/wrong_date_invoices")
    public HttpEntity<?> getWrongDateInvoices(){
        ApiResponse apiResponse = invoiceService.getWrongDateInvoices();
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/overpaid_invoices")
    public HttpEntity<?> getOverpaidInvoices(){
        ApiResponse apiResponse =invoiceService.getOverpaidInvoices();
        return ResponseEntity.ok(apiResponse);
    }

}
