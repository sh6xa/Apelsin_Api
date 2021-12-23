package com.example.kapitalbankapi.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Integer customerId;
    private Integer productId;
    private Integer quantity;
}
