package com.springboot.transaction.service.impl;

import com.springboot.transaction.dto.OrderRequest;
import com.springboot.transaction.dto.OrderResponse;
import com.springboot.transaction.entity.Order;
import com.springboot.transaction.entity.Payment;
import com.springboot.transaction.exception.PaymentException;
import com.springboot.transaction.repository.OrderRepository;
import com.springboot.transaction.repository.PaymentRepository;
import com.springboot.transaction.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private PaymentRepository paymentRepository;

    public OrderServiceImpl(OrderRepository orderRepository, PaymentRepository paymentRepository) {
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    @Transactional
    public OrderResponse placeOrder(OrderRequest orderRequest) {
        Order order=orderRequest.getOrder();
        order.setStatus("INPROGRESS");
        order.setOrderTrackingNumber(UUID.randomUUID().toString());
        orderRepository.save(order);

        Payment payment=orderRequest.getPayment();

        if(!payment.getType().equals("DEBIT")){
         throw new PaymentException("Payment card type do not support");
        }
        payment.setOrderId(order.getId());
        paymentRepository.save(payment);

        OrderResponse orderResponse=new OrderResponse();
        orderResponse.setOrderTrackingNumber(order.getOrderTrackingNumber());
        orderResponse.setStatus(order.getStatus());
        orderResponse.setMessage("SUCCESS");
        return orderResponse;
    }
}
