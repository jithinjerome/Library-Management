package com.libraryManagement.User;

import lombok.Data;

import java.time.LocalDate;

@Data
public class IssueReturnDTO {
    private String name;
    private String title;
    private LocalDate issueDate;
    private LocalDate returnDate;
}
