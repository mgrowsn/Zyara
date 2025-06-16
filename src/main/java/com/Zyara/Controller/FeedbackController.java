package com.Zyara.Controller;

import com.Zyara.Model.Feedback;
import com.Zyara.Service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class FeedbackController {
    @Autowired
    FeedbackService feedbackService;
    @PostMapping("/add-feedback")
    public ResponseEntity<String> addFeedback(@RequestBody Feedback feedback){
        return new ResponseEntity<>(feedbackService.addFeedback(feedback), HttpStatus.CREATED);
    }
    @GetMapping("/get-all-feedbacks")
    public ResponseEntity<List<Feedback>> getAllFeedbacks(){
        return new ResponseEntity<>(feedbackService.getAllFeedbacks(), HttpStatus.OK);
    }
}
