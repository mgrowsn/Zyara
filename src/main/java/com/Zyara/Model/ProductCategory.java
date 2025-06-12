package com.Zyara.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProductCategory {
    @Id
    private String id;
    private String image;
    private String name;

    @OneToMany(mappedBy = "product")
    private List<SubProduct> subProducts;
}
