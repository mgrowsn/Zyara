package com.Zyara.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Feedback {
    @Id
    private int feedbackId;
    private int productId;
    private String feedback;
    private String userEmail;
    private int rating;
}
