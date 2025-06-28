package com.Zyara.ControllerTest;

import com.Zyara.Controller.FeedbackController;
import com.Zyara.Model.Feedback;
import com.Zyara.Service.FeedbackService;
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
public class FeedbackControllerTest {
    @InjectMocks
    FeedbackController feedbackController;
    @Mock
    FeedbackService feedbackService;

    @Test
    public void addFeedbackTest(){
        Feedback feedback=new Feedback();
        when(feedbackService.addFeedback(feedback)).thenReturn("Feedback added successfully");
        ResponseEntity<String> ans=feedbackController.addFeedback(feedback);
        assertEquals("Feedback added successfully",ans.getBody());
        assertEquals(HttpStatus.CREATED,ans.getStatusCode());
    }
    @Test
    public void getAllFeedbacksTest(){
        List<Feedback> list=List.of(new Feedback());
        when(feedbackService.getAllFeedbacks()).thenReturn(list);
        ResponseEntity<List<Feedback>> ans=feedbackController.getAllFeedbacks();
        assertEquals(list,ans.getBody());
        assertEquals(HttpStatus.OK,ans.getStatusCode());
    }

}
