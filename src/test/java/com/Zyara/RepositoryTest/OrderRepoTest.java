package com.Zyara.RepositoryTest;

import com.Zyara.Model.Feedback;
import com.Zyara.Model.Orders;
import com.Zyara.Repository.OrderRepo;
import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class OrderRepoTest {
    @Autowired
    OrderRepo orderRepo;
    @BeforeEach
    public void setUp(){
        Orders orders1 = new Orders(1, "user1@gmail.com", "PP01", "Confirmed", LocalDate.now());
        Orders orders2 = new Orders(2, "user2@gmail.com", "PP02", "Confirmed", LocalDate.now());
        orderRepo.saveAll(List.of(orders1,orders2));
    }

    @Test
    public void saveOrderTest(){
        Orders orders1 = new Orders(3, "user@gmail.com", "PP01", "Confirmed", LocalDate.now());
        Orders saved=orderRepo.save(orders1);
        assertEquals(3,saved.getOrderId());
    }
    @Test
    public void findFeedbackByIdTest(){
        Optional<Orders> saved=orderRepo.findById(2);
        assertEquals("PP02",saved.get().getProdutId());
    }
    @Test
    public void deleteFeedbackByIdTest(){
        orderRepo.deleteById(2);
        assertFalse(orderRepo.findById(2).isPresent());
    }
    @Test
    public void getAllFeedbackTest(){
        List<Orders> saved=orderRepo.findAll();
        assertNotNull(saved);
    }
}

