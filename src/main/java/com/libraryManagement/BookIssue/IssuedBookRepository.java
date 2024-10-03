package com.libraryManagement.BookIssue;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface IssuedBookRepository extends JpaRepository<IssuedBooks , Long> {



    List<IssuedBooks> findByuserId(Long userId);
}
