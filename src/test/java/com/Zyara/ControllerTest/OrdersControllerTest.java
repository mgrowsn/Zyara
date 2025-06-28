package com.Zyara.ControllerTest;

import com.Zyara.Controller.OrdersController;
import com.Zyara.Dto.OrderDto;
import com.Zyara.Model.Orders;
import com.Zyara.Service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrdersControllerTest {
    @InjectMocks
    OrdersController ordersController;
    @Mock
    OrderService orderService;

    @Test
    public void addOrderTest(){
        OrderDto orderDto=new OrderDto();
        when(orderService.addOrder(orderDto)).thenReturn("Order placed successfully");
        ResponseEntity<String> ans=ordersController.addOrder(orderDto);
        assertEquals("Order placed successfully",ans.getBody());
        assertEquals(HttpStatus.CREATED,ans.getStatusCode());
    }

    @Test
    public void getAllOrdersTest(){
        List<Orders> list=List.of(new Orders());
        when(orderService.getAllOrders()).thenReturn(list);
        ResponseEntity<List<Orders>> ans=ordersController.getAllOrders();
        assertEquals(list,ans.getBody());
        assertEquals(HttpStatus.OK,ans.getStatusCode());
    }
}
