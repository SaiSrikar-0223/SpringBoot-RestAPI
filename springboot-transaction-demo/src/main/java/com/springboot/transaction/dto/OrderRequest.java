package com.springboot.transaction.dto;

import com.springboot.transaction.entity.Order;
import com.springboot.transaction.entity.Payment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
    private Order order;
    private Payment payment;
}
