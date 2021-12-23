package com.example.kapitalbankapi.service;

import com.example.kapitalbankapi.entity.*;
import com.example.kapitalbankapi.payload.ApiResponse;
import com.example.kapitalbankapi.payload.OrderDto;
import com.example.kapitalbankapi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private DetailRepository detailRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;

    public ApiResponse getByCustomer(Integer id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (!optionalCustomer.isPresent()) return new ApiResponse("Customer does not exists!", false);

        List<Order> allByCustomer_id = orderRepository.findAllByCustomer_Id(id);
        return new ApiResponse("Success!", true, allByCustomer_id);

    }

    public ApiResponse getAllOrderByDate(Date dateFrom, Date dateTo) {
        List<Order> allByDateBetween = orderRepository.findAllByDateBetween(dateFrom, dateTo);
        return new ApiResponse("Success", true, allByDateBetween);
    }

    public ApiResponse getLastOrders() {
        List<?> lastOrders = orderRepository.getLastOrders();
        return new ApiResponse("Success", true, lastOrders);
    }

    public ApiResponse getOrdersWithoutDetails() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String format = dateFormat.format(date);
        List<?> ordersWithoutDetails = orderRepository.getOrdersWithoutDetails(date);
        return new ApiResponse("Success", true, ordersWithoutDetails);
    }

    public ApiResponse getNumberOfProductsInYear() {
        List<?> numberOfProductsInYear = orderRepository.getNumberOfProductsInYear();
        return new ApiResponse("Success", true, numberOfProductsInYear);
    }

    public ApiResponse getOrderDetails(Integer id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (!optionalOrder.isPresent()) return new ApiResponse("Not Found", false);
        List<?> detailsByOrderId = orderRepository.getDetailsByOrderId(id);
        return new ApiResponse("Success", true, detailsByOrderId);
    }

    public ApiResponse postOrder(OrderDto orderDto) {
        Optional<Product> optionalProduct = productRepository.findById(orderDto.getProductId());
        if (!optionalProduct.isPresent()) return new ApiResponse("Not Found", false);
        Optional<Customer> optionalCustomer = customerRepository.findById(orderDto.getCustomerId());
        if (!optionalCustomer.isPresent()) return new ApiResponse("Not Found", false);

        Order order = new Order();
        order.setCustomer(optionalCustomer.get());
        order.setDate(new Date());

        orderRepository.save(order);

        Detail detail = new Detail();
        detail.setOrder(order);
        detail.setProduct(optionalProduct.get());
        detail.setQuantity(orderDto.getQuantity());
        detailRepository.save(detail);

        Invoice invoice = new Invoice();
        invoice.setIssued(new Date());
        invoice.setDue(new Date());
        invoice.setOrder(order);
       Double sum =  detail.getQuantity()*detail.getProduct().getPrice();
        invoice.setAmount(sum);
        invoiceRepository.save(invoice);
        return new ApiResponse("Success",true,invoice.getId());

    }
}