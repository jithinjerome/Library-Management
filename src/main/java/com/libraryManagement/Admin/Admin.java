package com.libraryManagement.Admin;

import com.libraryManagement.User.Role;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;


    public Admin() {}


    public Admin(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
