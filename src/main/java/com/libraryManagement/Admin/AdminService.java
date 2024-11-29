package com.libraryManagement.Admin;

import com.libraryManagement.JWT.JwtUtil;
import com.libraryManagement.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;

    public ResponseEntity<?> login(String email, String password) {
        Optional<Admin> adminOptional = adminRepository.findByEmail(email);

        if (adminOptional.isPresent()) {
            Admin admin = adminOptional.get();


            System.out.println("Admin found: " + admin.getEmail());
            System.out.println("Stored password: " + admin.getPassword());

            if (passwordEncoder.matches(password, admin.getPassword())) {
                String token = jwtUtil.generateToken(admin.getEmail(), admin.getRole().name());
                return ResponseEntity.ok().body(token);
            } else {
                return ResponseEntity.badRequest().body("Invalid credentials: Password mismatch");
            }
        }

        return ResponseEntity.badRequest().body("Invalid credentials: Admin not found");
    }


//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Optional<Admin> userOptional = adminRepository.findByEmail(email);
//        if (userOptional.isPresent()) {
//            Admin user = userOptional.get();
//            return org.springframework.security.core.userdetails.User
//                    .withUsername(user.getEmail())
//                    .password(user.getPassword())
//                    .authorities(new String[]{"ROLE_" + user.getRole().name()})
//                    .build();
//        }
//        throw new UsernameNotFoundException("User not found");
//    }
}
