package com.Zyara.Controller;

import com.Zyara.Dto.SignIn;
import com.Zyara.Service.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class SignInController {
    @Autowired
    SignInService signInService;

    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@RequestBody SignIn signIn) {
        return new ResponseEntity<>(signInService.signIn(signIn), HttpStatus.OK);
    }
}
