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
public class Product {
    @Id
    private String id;
    private String name;
    private String image;
    private String description;
    private String categoryId;
    private double price;
    private int rating;
    private int totalCapacity;
    private String brand;
    private int discount;
}
