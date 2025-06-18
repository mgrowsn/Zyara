package com.Zyara.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    private String id;
    private String name;
    private String image;
    private String description;
    private String categoryId;
    private double price;
    private int rating;
    private int quantity;
}
