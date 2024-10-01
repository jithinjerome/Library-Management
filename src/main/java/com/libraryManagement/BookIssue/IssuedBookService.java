package com.libraryManagement.BookIssue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssuedBookService {

    @Autowired
    private IssuedBookRepository issuedBookRepository;

    public List<IssuedBooks> issuedUsers() {
        return issuedBookRepository.findAll();
    }
}
