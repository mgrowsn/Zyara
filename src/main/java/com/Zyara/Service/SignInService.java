package com.Zyara.Service;

import com.Zyara.Dto.SignIn;
import com.Zyara.Jwt.JwtUtil;
import com.Zyara.Model.SignUp;
import com.Zyara.Repository.SignUpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignInService {
    @Autowired
    SignUpRepo signUpRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtil jwtUtil;
    public String signIn(SignIn signIn) {
        SignUp signUp=signUpRepo.findById(signIn.getEmail()).orElse(null);
        if(signUp==null) {
            return "You are not a resgistered user. Please sign up first.";
        }
        if(passwordEncoder.matches(signIn.getPassword(), signUp.getPassword())){
            return jwtUtil.generateToken(signIn.getEmail());
        }
        return "Please provide correct password";
    }
}
