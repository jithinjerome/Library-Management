package com.libraryManagement.Librarian;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookReturnDTO {

    private String name;
    private String title;
    private LocalDate returnDate;

}
