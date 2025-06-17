package com.Zyara.Service;

import com.Zyara.Dto.OrderDto;
import com.Zyara.Model.Orders;
import com.Zyara.Repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepo orderRepo;
    public String addOrder(OrderDto orderDto) {
        Orders order = new Orders();
        order.setOrderId(orderDto.getOrderId());
        order.setUserEmail(orderDto.getUserEmail());
        order.setProdutId(orderDto.getProductId());
        order.setStatus("Confirmed");
        order.setLocalDate(LocalDate.now());
        orderRepo.save(order);
        return "Order placed successfully";
    }

    public List<Orders> getAllOrders() {
        List<Orders> list=orderRepo.findAll();
        return list;
    }
}
