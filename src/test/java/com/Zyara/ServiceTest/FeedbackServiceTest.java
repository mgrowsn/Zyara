package com.Zyara.ServiceTest;

import com.Zyara.Model.Feedback;
import com.Zyara.Model.Product;
import com.Zyara.Repository.FeedbackRepo;
import com.Zyara.Repository.ProductRepo;
import com.Zyara.Service.FeedbackService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FeedbackServiceTest {
    @InjectMocks
    FeedbackService feedbackService;
    @Mock
    FeedbackRepo feedbackRepo;
    @Mock
    ProductRepo productRepo;

    @Test
    public void addFeedbackTest() {
        Feedback feedback1=new Feedback();
        feedback1.setProductId("1");
        Product product=new Product();
        when(productRepo.findById("1")).thenReturn(Optional.of(product));
        when(feedbackRepo.save(feedback1)).thenReturn(feedback1);
        String result=feedbackService.addFeedback(feedback1);
        assertEquals("Feedback added successfully",result);
    }
    @Test
    public void addFeedbackButProductNotFound() {
        Feedback feedback=new Feedback();
        feedback.setProductId("1");
        Product product=new Product();
        when(productRepo.findById("1")).thenReturn(Optional.empty());
        String result=feedbackService.addFeedback(feedback);
        assertEquals("Product not found",result);
    }
    @Test
    public void getAllFeedbacksTest() {
        Feedback feedback=new Feedback();
        List<Feedback> list=List.of(feedback);
        when(feedbackRepo.findAll()).thenReturn(list);
        List<Feedback> feedbacks=feedbackService.getAllFeedbacks();
        assertEquals(feedbacks.size(),list.size());
    }
}
