package com.aoftion.sdk2.dynamodb.connect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aoftion.sdk2.dynamodb.connect.entity.OrderEntity;
import com.aoftion.sdk2.dynamodb.connect.repository.OrderRepository;

@Service
public class ConnectOrderService {
    @Autowired
    OrderRepository orderRepository;

    public String getOrderValue(String orderID, String custermoerID) {
        OrderEntity orderEntity = orderRepository.getOrder(orderID, custermoerID);
        return String.valueOf(orderEntity.getOrderValue());
    }
}
