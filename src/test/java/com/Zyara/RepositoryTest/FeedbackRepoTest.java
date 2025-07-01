package com.Zyara.RepositoryTest;

import com.Zyara.Model.Feedback;
import com.Zyara.Repository.FeedbackRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class FeedbackRepoTest {
    @Autowired
    FeedbackRepo feedbackRepo;
    @BeforeEach
    public void setUp(){
        Feedback feedback1 = new Feedback(1, "P001", "Great product", "user1@example.com", 5);
        Feedback feedback2 = new Feedback(2, "P002", "Not bad", "user2@example.com", 3);
        feedbackRepo.saveAll(List.of(feedback1,feedback2));
    }

    @Test
    public void saveFeedbackTest(){
        Feedback feedback = new Feedback(3, "P002", "Not bad", "user2@example.com", 3);
        Feedback saved=feedbackRepo.save(feedback);
        assertEquals(3,saved.getFeedbackId());
    }
    @Test
    public void findFeedbackByIdTest(){
        Optional<Feedback> saved=feedbackRepo.findById(2);
        assertEquals("P002",saved.get().getProductId());
    }
    @Test
    public void deleteFeedbackByIdTest(){
        feedbackRepo.deleteById(2);
        assertFalse(feedbackRepo.findById(2).isPresent());
    }
    @Test
    public void getAllFeedbackTest(){
        List<Feedback> saved=feedbackRepo.findAll();
        assertNotNull(saved);
    }
}
