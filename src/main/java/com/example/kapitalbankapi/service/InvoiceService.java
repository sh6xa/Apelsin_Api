package com.example.kapitalbankapi.service;

import com.example.kapitalbankapi.entity.Invoice;
import com.example.kapitalbankapi.entity.Order;
import com.example.kapitalbankapi.payload.ApiResponse;
import com.example.kapitalbankapi.repository.InvoiceRepository;
import com.example.kapitalbankapi.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    OrderRepository orderRepository;
    public ApiResponse getInvoiceIssued() {
        List<?> allByIssued = invoiceRepository.getAllByIssued();
        return new ApiResponse("Success",true,allByIssued);

    }

    public ApiResponse getWrongDateInvoices() {
        List<?> wrongDateInvoices = invoiceRepository.getWrongDateInvoices();
        return new ApiResponse("Success",true,wrongDateInvoices);
    }


    public ApiResponse getOverpaidInvoices() {
        List<?> overpaidInvoices = invoiceRepository.getOverpaidInvoices();
        return new ApiResponse("Success",true,overpaidInvoices);
    }
}
