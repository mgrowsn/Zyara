package com.Zyara.Controller;

import com.Zyara.Dto.OrderDto;
import com.Zyara.Model.Orders;
import com.Zyara.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class OrdersController {
    @Autowired
    OrderService orderService;

    @PostMapping("/add-orders")
    public ResponseEntity<String> addOrder(@RequestBody OrderDto orderDto){
        return new ResponseEntity<>(orderService.addOrder(orderDto), HttpStatus.CREATED);
    }

    @GetMapping("/get-all-orders")
    public ResponseEntity<List<Orders>> getAllOrders(){
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }
}
