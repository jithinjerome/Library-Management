package com.libraryManagement.BookIssue;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IssuedBookRepository extends JpaRepository<IssuedBooks , Long> {
}
