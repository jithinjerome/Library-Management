package com.libraryManagement.JWT;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/user/login", //User login
                                "/api/user/register", //User registration
                                "/api/admin/login", //Admin login
                                "/api/librarian/register", //Librarian registration
                                "/api/librarian/login") //librarian login
                        .permitAll()
                        .requestMatchers(
                                "/api/book/books", //All books
                                "/api/book/books/category/{id}", //Book by category
                                "/api/book/books/{author}", //Book by author
                                "/api/book/books/language/{id}", //Book by language
                                "/api/book/books/{catId}/{lanId}", //Book by category and language
                                "/api/categoryDetails/getCategory", //Get all categories
                                "/api/language/getLanguage") //Get all language
                        .authenticated()
                        .requestMatchers(
                                "/api/book/addBook", //Add book
                                "/api/book/update/{id}", //Book update
                                "/api/book/category/{id}", //Category update
                                "/api/book/language/{id}", //Language update
                                "/api/book/delete/{id}", //Delete book
                                "/api/categoryDetails/addCategories", // Add category
                                "/api/language/addLanguage") // Add Language
                        .hasRole("ADMIN")
                        .requestMatchers(
                                "/api/librarian/issue/{bookId}/{userId}", //Book issuing
                                "/api/librarian/{userId}/issuedBooksWithReturn", //Issued book details
                                "/api/librarian/return/{bookId}/{userId}", // Book return
                                "/api/librarian/returnBooks/{userId}") // Return book details
                        .hasRole("LIBRARIAN")


                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
