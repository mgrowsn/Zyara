package com.Zyara.RepositoryTest;

import com.Zyara.Model.SignUp;
import com.Zyara.Repository.SignUpRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class SignUpRepoTest {
    @Autowired
    SignUpRepo signUpRepo;

    @Test
    public void saveTest(){
        SignUp signUp=new SignUp();
        signUp.setEmail("mehak@gmail.com");
        SignUp saved=signUpRepo.save(signUp);
        assertEquals("mehak@gmail.com",saved.getEmail());
    }
    @Test
    public void findByIdTest(){
        SignUp signUp=new SignUp();
        signUp.setEmail("mehak2@gmail.com");
        signUp.setFirstName("Mehak");
        signUpRepo.save(signUp);
        Optional<SignUp> saved=signUpRepo.findById(signUp.getEmail());
        assertTrue(saved.isPresent());
        assertEquals("Mehak",saved.get().getFirstName());
    }
    @Test
    public void existByIdTest(){
        SignUp signUp=new SignUp();
        signUp.setEmail("mehak3@gmail.com");
        signUp.setFirstName("Mehak");
        signUpRepo.save(signUp);
        boolean saved=signUpRepo.existsById(signUp.getEmail());
        assertTrue(saved);
    }
}
