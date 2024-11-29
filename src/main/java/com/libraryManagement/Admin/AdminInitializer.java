package com.libraryManagement.Admin;

import com.libraryManagement.User.Role;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminInitializer {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //For creating a default admin with predefined credentials if there is no admin in database.
    @PostConstruct
    public void initializeAdmin() {
        if (adminRepository.count() == 0) {
            String defaultEmail = "admin@library.com";
            String defaultPassword = "admin123";
            Role defaultRole = Role.ADMIN;

            Admin defaultAdmin = new Admin();
            defaultAdmin.setEmail(defaultEmail);
            defaultAdmin.setPassword(passwordEncoder.encode(defaultPassword));
            defaultAdmin.setRole(defaultRole);

            adminRepository.save(defaultAdmin);

            System.out.println("Default Admin created:");
            System.out.println("Email: " + defaultEmail);
            System.out.println("Password: " + defaultPassword);
        } else {
            System.out.println("Admins already exist. No default admin created.");
        }
    }
}
