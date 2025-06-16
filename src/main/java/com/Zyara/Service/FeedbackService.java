package com.Zyara.Service;

import com.Zyara.Model.Feedback;
import com.Zyara.Model.Product;
import com.Zyara.Repository.FeedbackRepo;
import com.Zyara.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {
    @Autowired
    FeedbackRepo feedbackRepo;
    @Autowired
    ProductRepo productRepo;
    public String addFeedback(Feedback feedback) {
        Product product=productRepo.findById(feedback.getProductId()).orElse(null);
        if(product == null) {
            return "Product not found";
        }
        feedbackRepo.save(feedback);
        return "Feedback added successfully";
    }

    public List<Feedback> getAllFeedbacks() {
        List<Feedback> feedbacks=feedbackRepo.findAll();
        return feedbacks;
    }
}
