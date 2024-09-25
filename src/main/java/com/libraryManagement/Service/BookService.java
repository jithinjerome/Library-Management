package com.libraryManagement.Service;

import com.libraryManagement.Model.Book;
import com.libraryManagement.Model.Category;
import com.libraryManagement.Repository.BookRepository;
import com.libraryManagement.Repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CategoryRepository categoryRepository;

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

    public ResponseEntity<?> addBook(Long categoryId, Book book) {
        Book book1 = new Book();
        Optional<Category>categoryOptional=categoryRepository.findById(categoryId);
        if (categoryOptional.isPresent()){
            Category category = categoryOptional.get();
            book1.setCategoryId(categoryId);
            book1.setCategory(category.getCategoryName());
            book1.setTitle(book.getTitle());
            book1.setAuthor(book.getAuthor());
            bookRepository.save(book1);
            return new ResponseEntity<>(book1,HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Category isn't present", HttpStatus.BAD_REQUEST);
        }
        //return new ResponseEntity<>("Something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
