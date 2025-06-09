package com.Zyara.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    private String id;
    private String name;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "sub_product_id")
    private SubProduct subProduct;
}
