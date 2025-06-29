package com.Zyara.ControllerTest;

import com.Zyara.Controller.SignInController;
import com.Zyara.Dto.SignIn;
import com.Zyara.Service.SignInService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
public class SignInControllerTest {
    @InjectMocks
    SignInController signInController;
    @Mock
    SignInService signInService;

    @Test
    public void signInTest() {
        SignIn signIn=new SignIn();
        when(signInService.signIn(signIn)).thenReturn("generate-token-user-email");
        ResponseEntity<String> ans=signInController.signIn(signIn);
        assertEquals("generate-token-user-email",ans.getBody());
        assertEquals(HttpStatus.OK,ans.getStatusCode());
    }
}
