package com.libraryManagement.BookReturn;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReturnBookRepository extends JpaRepository<ReturnBooks, Long> {

    Optional<ReturnBooks> findByUserIdAndBookId(Long userId, Long bId);
}
