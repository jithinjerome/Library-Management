package com.libraryManagement.Librarian;

import com.libraryManagement.User.Role;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Librarian")
public class Librarian {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Transient
    private String token;

    // Constructor with parameters
    public Librarian(String name, String email, String phone, String password, Role role) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = role;
    }

    // Default constructor
    public Librarian() {}
}
