package com.aoftion.sdk2.dynamodb.connect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aoftion.sdk2.dynamodb.connect.service.ConnectOrderService;

import jakarta.websocket.server.PathParam;

@RequestMapping("/")
@RestController
public class ConnectRestController {
    @Autowired
    ConnectOrderService connectOrderService;

    @GetMapping
    private String welcome() {
        return "Welcome to Connect DynamoDB";
    }

    @GetMapping("/find")
    public ResponseEntity<String> findOrderValue(@PathParam("order_id") String orderID, @PathParam("customer_id") String customerID) {
        String value = connectOrderService.getOrderValue(orderID, customerID);
        return ResponseEntity.status(HttpStatus.OK).body(value);
    }
}
