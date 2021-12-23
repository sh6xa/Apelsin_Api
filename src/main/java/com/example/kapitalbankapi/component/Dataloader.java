package com.example.kapitalbankapi.component;

import com.example.kapitalbankapi.entity.*;
import com.example.kapitalbankapi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.security.Timestamp;
import java.util.Date;

@Component
public class Dataloader implements CommandLineRunner {
   @Autowired
    CategoryRepository categoryRepository;
   @Autowired
    CustomerRepository customerRepository;
   @Autowired
    DetailRepository detailRepository;
   @Autowired
    InvoiceRepository invoiceRepository;
   @Autowired
    OrderRepository orderRepository;
   @Autowired
   PaymentRepository paymentRepository;
   @Autowired
   ProductRepository productRepository;




    @Value("${spring.sql.init.mode}")
    private String initMode;

    @Override
    public void run(String... args) throws Exception {


        if (initMode.equals("always")){
            Customer customer = customerRepository.save(new Customer("Shaxzod", "Uzbekistan", "navoiy1", "977777777"));
            Customer customer2 = customerRepository.save(new Customer("Avaz", "Uzbekistan", "navoiy2", "977777776"));
            Customer customer3 = customerRepository.save(new Customer("Bahrom", "china", "navoiy3", "977777775"));
            Customer customer4 = customerRepository.save(new Customer("Abror", "kazak", "navoiy4", "977777774"));
            Customer customer5 = customerRepository.save(new Customer("Ahror", "kazak", "navoiy5", "977777773"));
            Order order = orderRepository.save(new Order(new Date(),customer));
            Order order2 = orderRepository.save(new Order(new Date(),customer2));
            Order order3 = orderRepository.save(new Order(new Date(),customer3));
            Order order4 = orderRepository.save(new Order(new Date(),customer4));
            Order order5 = orderRepository.save(new Order(new Date(),customer5));
            Invoice invoice = invoiceRepository.save(new Invoice(order, 100000, new Date(), new Date()));
            Invoice invoice2 = invoiceRepository.save(new Invoice(order2, 100000, new Date(), new Date()));
            Invoice invoice3 = invoiceRepository.save(new Invoice(order3, 100000, new Date(), new Date()));
            Invoice invoice4 = invoiceRepository.save(new Invoice(order4, 100000, new Date(), new Date()));
            Invoice invoice5 = invoiceRepository.save(new Invoice(order5, 100000, new Date(), new Date()));
            Category category = categoryRepository.save(new Category("Phone"));
            Category category2 = categoryRepository.save(new Category("Laptop"));
            Category category3 = categoryRepository.save(new Category("Food"));
            Category category4 = categoryRepository.save(new Category("Computer"));
            Category category5 = categoryRepository.save(new Category("TV"));
            Product product = productRepository.save(new Product("Iphone", "Iphone 11 pro", 1200, category));
            Product product2 = productRepository.save(new Product("Macbook", "Macbook m1 pro", 2200, category2));
            Product product3 = productRepository.save(new Product("Macbook", "Macbook m2 pro", 2200, category2));
            Product product4 = productRepository.save(new Product("Macbook", "Macbook m3 pro", 2200, category2));
            Product product5 = productRepository.save(new Product("Macbook", "Macbook m4 pro", 2200, category2));
            Detail detail = detailRepository.save(new Detail(order, product, 4));
            Detail detail2 = detailRepository.save(new Detail(order2, product2, 3));
            Detail detail3 = detailRepository.save(new Detail(order3, product2, 3));
            Detail detail4 = detailRepository.save(new Detail(order4, product2, 3));
            Detail detail5 = detailRepository.save(new Detail(order5, product2, 3));
            paymentRepository.save(new Payment(new Date(),100000d,invoice));
            paymentRepository.save(new Payment(new Date(),100000d,invoice2));
            paymentRepository.save(new Payment(new Date(),100000d,invoice3));
            paymentRepository.save(new Payment(new Date(),100000d,invoice4));
            paymentRepository.save(new Payment(new Date(),100000d,invoice5));


        }
    }
}
