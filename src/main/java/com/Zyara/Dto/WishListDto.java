package com.Zyara.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishListDto {
    private String id;
    private String name;
    private String image;
    private double price;
}
