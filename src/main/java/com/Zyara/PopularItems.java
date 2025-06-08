package com.Zyara;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PopularItems {
    private String image;
    private String name;
    private String price;
}
