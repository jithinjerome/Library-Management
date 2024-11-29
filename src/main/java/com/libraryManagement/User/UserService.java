package com.libraryManagement.User;

import com.libraryManagement.JWT.JwtUtil;
import com.libraryManagement.Librarian.Librarian;
import com.libraryManagement.Librarian.LibrarianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public Object registerUser(User user) {
        if (user.getRole() != null && user.getRole() == Role.ADMIN) {
            throw new IllegalArgumentException("Registration as ADMIN is not allowed.");
        }

        if (user.getRole() == null) {
            user.setRole(Role.USER);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }




    public ResponseEntity<?> loginUser(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
                return ResponseEntity.ok().body(token);
            } else {
                return ResponseEntity.badRequest().body("Invalid credentials: Password mismatch");
            }
        }
        return ResponseEntity.badRequest().body("Invalid credentials: User not found");
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getEmail())
                    .password(user.getPassword())
                    .authorities(new String[]{"ROLE_" + user.getRole().name()})
                    .build();
        }
        throw new UsernameNotFoundException("User not found");
    }
}
