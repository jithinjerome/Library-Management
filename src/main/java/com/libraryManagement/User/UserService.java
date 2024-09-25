package com.libraryManagement.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    private final String adminEmail = "admin@example.com";
    private final String adminPassword = "admin123";

    public User registerUser(User user){
        user.setRole(Role.USER);
        return userRepository.save(user);
    }

    public ResponseEntity<?> loginUser(String email , String password){

        if(email.equals(adminEmail) && password.equals(adminPassword)){
            return new ResponseEntity<>("Login Successfull as Admin",HttpStatus.OK);
        }

        Optional<User> userOptional = userRepository.findByEmailAndPassword(email,password);
        if(userOptional.isPresent())
        {
            return new ResponseEntity<>("Login Successfully", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Login failed",HttpStatus.BAD_REQUEST);
        }
    }
}
