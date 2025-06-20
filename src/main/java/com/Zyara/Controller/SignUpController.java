package com.Zyara.Controller;

import com.Zyara.Model.SignUp;
import com.Zyara.Service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class SignUpController {
    @Autowired
    private SignUpService signUpService;
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody SignUp signup) {
        return new ResponseEntity<>(signUpService.signUp(signup), HttpStatus.CREATED);
    }
    @GetMapping("/get-all-signup-users")
    public ResponseEntity<List<SignUp>> getAllSignUpUsers() {
        return new ResponseEntity<>(signUpService.getAllSignUpUsers(), HttpStatus.OK);
    }
}
