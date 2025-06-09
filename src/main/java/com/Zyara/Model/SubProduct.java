package com.Zyara.Model;

import com.Zyara.Model.Item;
import com.Zyara.Model.Products;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SubProduct {
    @Id
    private String id;
    private String name;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "product_id")
    private Products product;

    @OneToMany(mappedBy = "subProduct")
    private List<Item> items;
}
