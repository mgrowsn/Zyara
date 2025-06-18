package com.Zyara.Service;

import com.Zyara.Dto.SignIn;
import com.Zyara.Model.SignUp;
import com.Zyara.Repository.SignUpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignInService {
    @Autowired
    SignUpRepo signUpRepo;
    public String signIn(SignIn signIn) {
        SignUp signUp=signUpRepo.findById(signIn.getEmail()).orElse(null);
        if(signUp==null) {
            return "You are not a resgistered user. Please sign up first.";
        }
        if(signIn.getPassword().equals(signUp.getPassword())) {
            return "Login successful";
        }
        return "Please provide correct password";
    }
}
