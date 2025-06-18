package com.Zyara.Service;

import com.Zyara.Model.SignUp;
import com.Zyara.Repository.SignUpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SignUpService {
    @Autowired
    SignUpRepo signUpRepo;
    public String signUp(SignUp signup) {
        if(!validateSignUp(signup).equals("Valid SignUp")){
            return validateSignUp(signup);
        }
        signUpRepo.save(signup);
        return "User signed up successfully";
    }

    private String validateSignUp(SignUp signup) {
        if(signup.getFirstName()==null || signup.getFirstName().isEmpty()) {
            return "First name is required";
        }
        if(!signup.getFirstName().matches("^[a-zA-Z]+$")){
            return "First name should contain only alphabets";
        }
        if(signup.getLastName()==null || signup.getLastName().isEmpty()) {
            return "Last name is required";
        }
        if(!signup.getLastName().matches("^[a-zA-Z]+$")){
            return "Last name should contain only alphabets";
        }
        if(signup.getPassword()==null || signup.getPassword().isEmpty()) {
            return "Password is required";
        }
        if(signup.getConfirmPassword()==null || signup.getConfirmPassword().isEmpty()) {
            return "Confirm Password is required";
        }
        if(!signup.getPassword().equals(signup.getConfirmPassword())) {
            return "Password and Confirm Password should match";
        }
        if(signUpRepo.existsById(signup.getEmail())) {
            return "Email already exists";
        }
        return "Valid SignUp";
    }

    public List<SignUp> getAllSignUpUsers() {
        return signUpRepo.findAll();
    }
}
