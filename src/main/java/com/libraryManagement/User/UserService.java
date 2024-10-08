package com.libraryManagement.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final String adminEmail = "admin@example.com";
    private final String adminPassword = "$2a$10$7Q1bZ/lZtR7uNSxQAhEw.eVtBxZZ9Jf/jfNWTq0WkO57nMx50Yuq."; //BCrypted "admin123"

    public User registerUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        return userRepository.save(user);
    }

    public ResponseEntity<?> loginUser(String email , String password){

        if(email.equals(adminEmail) && passwordEncoder.matches(password,adminPassword)){
            return new ResponseEntity<>("Login Successfull as Admin",HttpStatus.OK);
        }

        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isPresent())
        {
            User user = userOptional.get();
            if(passwordEncoder.matches(password,user.getPassword())){
                return new ResponseEntity<>("Login Successfull",HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Login Failed",HttpStatus.BAD_REQUEST);

    }
}
