package com.Zyara.ServiceTest;

import com.Zyara.Dto.OrderDto;
import com.Zyara.Model.Orders;
import com.Zyara.Repository.OrderRepo;
import com.Zyara.Service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @Mock
    OrderRepo orderRepo;
    @InjectMocks
    OrderService orderService;
    @Test
    public void addOrderTest() {
        OrderDto orderDto=new OrderDto();
        Orders order = new Orders();
        order.setOrderId(orderDto.getOrderId());
        order.setUserEmail(orderDto.getUserEmail());
        order.setProdutId(orderDto.getProductId());
        order.setStatus("Confirmed");
        order.setLocalDate(LocalDate.now());
        when(orderRepo.save(order)).thenReturn(order);
        String result=orderService.addOrder(orderDto);
        assertEquals("Order placed successfully",result);
    }

    @Test
    public void getAllOrdersTest() {
        Orders order=new Orders();
        List<Orders> list=List.of(order);
        when(orderRepo.findAll()).thenReturn(list);
        List<Orders> ordersList=orderService.getAllOrders();
        assertEquals(ordersList.size(),list.size());
    }
}
