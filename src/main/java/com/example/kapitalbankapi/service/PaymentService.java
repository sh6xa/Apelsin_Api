package com.example.kapitalbankapi.service;

import com.example.kapitalbankapi.controller.PaymentController;
import com.example.kapitalbankapi.entity.Invoice;
import com.example.kapitalbankapi.entity.Order;
import com.example.kapitalbankapi.entity.Payment;
import com.example.kapitalbankapi.payload.ApiResponse;
import com.example.kapitalbankapi.payload.PaymentDto;
import com.example.kapitalbankapi.repository.InvoiceRepository;
import com.example.kapitalbankapi.repository.OrderRepository;
import com.example.kapitalbankapi.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;

    public ApiResponse getAllByOrderId(Integer id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (!optionalOrder.isPresent()) return new ApiResponse("Order does not exists!",false);

        List<Payment> allByOrderId = paymentRepository.findAllByOrderId(id);
        return new ApiResponse("Success",true,allByOrderId);

    }

    public ApiResponse addPayment(PaymentDto paymentDto) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(paymentDto.getInvoiceId());
        if (!optionalInvoice.isPresent()) return new ApiResponse("Not Found",false);

        Payment payment = new Payment();
        payment.setInvoice(optionalInvoice.get());
        payment.setAmount(optionalInvoice.get().getAmount());
        payment.setTime(new Date());
        paymentRepository.save(payment);
        return new ApiResponse("Success",true,payment);
    }

    public ApiResponse getDetails(Integer id) {
        Optional<Payment> optionalPayment = paymentRepository.findById(id);
        if (!optionalPayment.isPresent()) return new ApiResponse("Not Found",false);
        return new ApiResponse("Success",true,optionalPayment.get());
    }
}
