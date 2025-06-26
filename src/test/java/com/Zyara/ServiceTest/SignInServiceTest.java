package com.Zyara.ServiceTest;

import com.Zyara.Dto.SignIn;
import com.Zyara.Jwt.JwtUtil;
import com.Zyara.Model.SignUp;
import com.Zyara.Repository.SignUpRepo;
import com.Zyara.Service.SignInService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SignInServiceTest {
    @Mock
    SignUpRepo signUpRepo;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    JwtUtil jwtUtil;
    @InjectMocks
    SignInService signInService;

    @Test
    public void signInTest() {
        SignIn signIn=new SignIn();
        signIn.setEmail("mehak@gmail.com");
        signIn.setPassword("Mehak@123");
        SignUp signUp=new SignUp();
        signUp.setPassword("Mehak@123");
        when(signUpRepo.findById("mehak@gmail.com")).thenReturn(Optional.of(signUp));
        when(passwordEncoder.matches("Mehak@123", "Mehak@123")).thenReturn(true);
        when(jwtUtil.generateToken("mehak@gmail.com")).thenReturn("encodeusermehak@123");
        String res=signInService.signIn(signIn);
        assertEquals("encodeusermehak@123",res);
    }

    @Test
    public void signInButUserIsNotResgistered() {
        SignIn signIn=new SignIn();
        signIn.setEmail("mehak@gmail.com");
        when(signUpRepo.findById("mehak@gmail.com")).thenReturn(Optional.empty());
        String res=signInService.signIn(signIn);
        assertEquals("You are not a resgistered user. Please sign up first.",res);
    }

    @Test
    public void signInwithIncorrectPassword() {
        SignIn signIn=new SignIn();
        signIn.setEmail("mehak@gmail.com");
        signIn.setPassword("Mehak@123");
        SignUp signUp=new SignUp();
        signUp.setPassword("Mehak@0123");
        when(signUpRepo.findById("mehak@gmail.com")).thenReturn(Optional.of(signUp));
        when(passwordEncoder.matches("Mehak@123", "Mehak@0123")).thenReturn(false);
        String res=signInService.signIn(signIn);
        assertEquals("Please provide correct password",res);
    }
}
