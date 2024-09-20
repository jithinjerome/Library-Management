package com.libraryManagement.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
