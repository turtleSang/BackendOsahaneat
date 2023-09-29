package com.thanksen.osahaneat.controller;

import com.thanksen.osahaneat.Service.imp.OrderServiceImp;
import com.thanksen.osahaneat.payload.Request.RequestOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderServiceImp orderServiceImp;

    public OrderController(OrderServiceImp orderServiceImp){
        this.orderServiceImp = orderServiceImp;
    }

    @PostMapping("")
    public ResponseEntity<?> createOrder(@RequestBody RequestOrder requestOrder){

        boolean check = orderServiceImp.createOrder(requestOrder);

        return new ResponseEntity<>(check, HttpStatus.OK);
    }

}
