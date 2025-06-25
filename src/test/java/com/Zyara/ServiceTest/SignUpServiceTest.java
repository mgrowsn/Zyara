package com.Zyara.ServiceTest;

import com.Zyara.Model.SignUp;
import com.Zyara.Repository.SignUpRepo;
import com.Zyara.Service.SignUpService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SignUpServiceTest {
    @InjectMocks
    SignUpService signUpService;
    @Mock
    SignUpRepo signUpRepo;
    @Mock
    PasswordEncoder passwordEncoder;

    @Test
    public void signUpTest() {
        SignUp signUp=new SignUp();
        signUp.setFirstName("Mehak");
        signUp.setLastName("Thaman");
        signUp.setEmail("mehak@example.com");
        signUp.setPassword("Mehak@123");
        signUp.setConfirmPassword("Mehak@123");
        when(signUpRepo.existsById("mehak@example.com")).thenReturn(false);
        when(signUpRepo.save(signUp)).thenReturn(signUp);
        when(passwordEncoder.encode("Mehak@123")).thenReturn("encode@123");
        String res=signUpService.signUp(signUp);
        assertEquals("User signed up successfully",res);
    }
    @Test
    public void signUpButEmailAlreadyExists() {
        SignUp signUp=new SignUp();
        signUp.setFirstName("Mehak");
        signUp.setLastName("Thaman");
        signUp.setEmail("mehak@example.com");
        signUp.setPassword("Mehak@123");
        signUp.setConfirmPassword("Mehak@123");
        when(signUpRepo.existsById("mehak@example.com")).thenReturn(true);
        String res=signUpService.signUp(signUp);
        assertEquals("Email already exists",res);
    }

    @Test
    public void getAllSignUpUsersTest() {
        SignUp signUp=new SignUp();
        List<SignUp> list=List.of(signUp);
        when(signUpRepo.findAll()).thenReturn(list);
        List<SignUp> ans=signUpService.getAllSignUpUsers();
        assertEquals(list.size(),ans.size());
    }

}
