package com.libraryManagement.Book;



import com.libraryManagement.Category.Category;
import com.libraryManagement.Category.CategoryRepository;
import com.libraryManagement.Language.Language;
import com.libraryManagement.Language.LanguageRepository;
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
    @Autowired
    private LanguageRepository languageRepository;

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

    public ResponseEntity<?> addBook(Long categoryId,Long languageId, Book book) {
        Book book1 = new Book();
        Optional<Category>categoryOptional=categoryRepository.findById(categoryId);
        Optional<Language> languageOptional = languageRepository.findById(languageId);
        if (categoryOptional.isPresent() && languageOptional.isPresent()){
            Category category = categoryOptional.get();
            Language language = languageOptional.get();
            book1.setCategoryId(categoryId);
            book1.setCategory(category.getCategoryName());
            book1.setLanguageId(languageId);
            book1.setLanguage(language.getLanguage());
            book1.setTitle(book.getTitle());
            book1.setAuthor(book.getAuthor());
            bookRepository.save(book1);
            return new ResponseEntity<>(book1,HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Category or Language isn't present", HttpStatus.BAD_REQUEST);
        }
        //return new ResponseEntity<>("Something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
