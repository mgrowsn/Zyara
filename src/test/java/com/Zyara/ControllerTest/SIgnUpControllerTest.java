package com.Zyara.ControllerTest;

import com.Zyara.Controller.SignUpController;
import com.Zyara.Model.SignUp;
import com.Zyara.Service.SignUpService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SIgnUpControllerTest {
    @InjectMocks
    SignUpController signUpController;
    @Mock
    private SignUpService signUpService;
    @Test
    public void signUpTest() {
        SignUp signup=new SignUp();
        when(signUpService.signUp(signup)).thenReturn("User signed up successfully");
        ResponseEntity<String> ans=signUpController.signUp(signup);
        assertEquals("User signed up successfully",ans.getBody());
        assertEquals(HttpStatus.CREATED,ans.getStatusCode());
    }
    @Test
    public void getAllSignUpUsersTest() {
        List<SignUp> list=List.of(new SignUp());
        when(signUpService.getAllSignUpUsers()).thenReturn(list);
        ResponseEntity<List<SignUp>> ans=signUpController.getAllSignUpUsers();
        assertEquals(list,ans.getBody());
        assertEquals(HttpStatus.OK,ans.getStatusCode());
    }
}
