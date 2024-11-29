package com.libraryManagement.JWT;

import com.libraryManagement.Admin.Admin;
import com.libraryManagement.Admin.AdminRepository;
import com.libraryManagement.Librarian.Librarian;
import com.libraryManagement.Librarian.LibrarianRepository;
import com.libraryManagement.User.User;
import com.libraryManagement.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private LibrarianRepository librarianRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Check in Admin repository
        Optional<Admin> adminOptional = adminRepository.findByEmail(email);
        if (adminOptional.isPresent()) {
            Admin admin = adminOptional.get();
            return org.springframework.security.core.userdetails.User
                    .withUsername(admin.getEmail())
                    .password(admin.getPassword())
                    .authorities("ROLE_" + admin.getRole().name())
                    .build();
        }

        // Check in Librarian repository
        Optional<Librarian> librarianOptional = librarianRepository.findByEmail(email);
        if (librarianOptional.isPresent()) {
            Librarian librarian = librarianOptional.get();
            return org.springframework.security.core.userdetails.User
                    .withUsername(librarian.getEmail())
                    .password(librarian.getPassword())
                    .authorities("ROLE_" + librarian.getRole().name())
                    .build();
        }

        // Check in User repository
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getEmail())
                    .password(user.getPassword())
                    .authorities("ROLE_" + user.getRole().name())
                    .build();
        }

        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}
