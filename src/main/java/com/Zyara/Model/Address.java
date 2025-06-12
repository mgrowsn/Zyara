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
public class Address {
    @Id
    private int id;
    private String address;
    private String city;
    private String country;
    private String phoneNumber;
    private String pincode;
    private String state;
    private String landmark;
}
