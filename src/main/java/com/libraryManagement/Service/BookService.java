package com.libraryManagement.Service;

import com.libraryManagement.Model.Book;
import com.libraryManagement.Repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book newBook(Book book)
    {
        return bookRepository.save(book);
    }

    @Transactional
    public Book updateBook(Long id, Book book)
    {
        Optional<Book> existingBook = bookRepository.findById(id);
        if(existingBook.isPresent())
        {
            Book updatedBook = existingBook.get();
            updatedBook.setTitle(book.getTitle());
            updatedBook.setAuthor(book.getAuthor());
            return bookRepository.save(updatedBook);
        }
        throw new RuntimeException("Book not found with id" + id);
    }

    public void deleteBookById(Long id) 
    {
        bookRepository.deleteById(id);
    }

}
