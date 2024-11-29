package com.libraryManagement.Librarian;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReturnBookRepository extends JpaRepository<ReturnBooks, Long> {

    Optional<ReturnBooks> findByUserIdAndBookId(Long userId, Long bId);

    List<ReturnBooks> findByUserId(Long id);
}
