package com.Zyara.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SignUp {
    @Id
    @Email(message = "Please enter a valid email address.")
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String confirmPassword;
}
