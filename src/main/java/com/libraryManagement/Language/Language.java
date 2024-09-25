package com.libraryManagement.Language;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "languages")
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "language")
    private String language;
}
