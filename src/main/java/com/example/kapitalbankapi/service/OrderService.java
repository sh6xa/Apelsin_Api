package com.example.kapitalbankapi.service;

import com.example.kapitalbankapi.entity.Customer;
import com.example.kapitalbankapi.entity.Order;
import com.example.kapitalbankapi.payload.ApiResponse;
import com.example.kapitalbankapi.repository.CustomerRepository;
import com.example.kapitalbankapi.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CustomerRepository customerRepository;

    public ApiResponse getByCustomer(Integer id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (!optionalCustomer.isPresent()) return new ApiResponse("Customer does not exists!",false);

        List<Order> allByCustomer_id = orderRepository.findAllByCustomer_Id(id);
        return new ApiResponse("Success!",true,allByCustomer_id);

    }

    public ApiResponse getAllOrderByDate(Date dateFrom, Date dateTo) {
        List<Order> allByDateBetween = orderRepository.findAllByDateBetween(dateFrom, dateTo);
        return new ApiResponse("Success",true,allByDateBetween);
    }

    public ApiResponse getLastOrders() {
        List<?> lastOrders = orderRepository.getLastOrders();
        return new ApiResponse("Success",true,lastOrders);
    }

    public ApiResponse getOrdersWithoutDetails() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String format = dateFormat.format(date);
        List<?> ordersWithoutDetails = orderRepository.getOrdersWithoutDetails(date);
        return new ApiResponse("Success",true,ordersWithoutDetails);
    }

    public ApiResponse getNumberOfProductsInYear() {
        List<?> numberOfProductsInYear = orderRepository.getNumberOfProductsInYear();
        return new ApiResponse("Success",true,numberOfProductsInYear);
    }

    public ApiResponse getOrderDetails(Integer id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (!optionalOrder.isPresent()) return new ApiResponse("Not Found",false);
        List<?> detailsByOrderId = orderRepository.getDetailsByOrderId(id);
        return new ApiResponse("Success",true,detailsByOrderId);
    }
}
