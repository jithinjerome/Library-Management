package com.libraryManagement.User;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static com.libraryManagement.User.Role.ADMIN;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                .requestMatchers("/books").hasAnyRole("ADMIN","USER")
                        .requestMatchers("/books/category/{id}").hasAnyRole("ADMIN","USER")
                        .requestMatchers( "/books/{author}").hasAnyRole("ADMIN","USER")
                        .requestMatchers("/books/language/{id}").hasAnyRole("ADMIN","USER")
                        .requestMatchers("/books/{catId}/{lanId}").hasAnyRole("ADMIN","USER")
                        .requestMatchers("/returnBooks/{userId}").hasRole("ADMIN")
                        .requestMatchers("/addBook").hasRole("ADMIN")
                        .requestMatchers("/update/{id}").hasRole("ADMIN")
                        .requestMatchers("/category/{id}").hasRole("ADMIN")
                        .requestMatchers("/language/{id}").hasRole("ADMIN")
                        .requestMatchers("/delete/{id}").hasRole("ADMIN")
                        .requestMatchers("/issue/{bookId}/{userId}").hasRole("ADMIN")
                        .requestMatchers("/return/{bookId}/{userId}").hasRole("ADMIN")
                        .requestMatchers("/addCategories").hasRole("ADMIN")
                        .requestMatchers("/getCategory").hasRole("ADMIN")
                        .requestMatchers("/addLanguage").hasRole("ADMIN")
                        .requestMatchers("/getLanguage").hasRole("ADMIN")
                        .requestMatchers("/register").hasRole("USER")
                        .requestMatchers("/login").hasAnyRole("ADMIN","USER")
                        .requestMatchers("/{userId}/issuedBooksWithReturn").hasRole("ADMIN")
                        .anyRequest().authenticated()
    ) .httpBasic(withDefaults());

    return http.build();
    }

}
