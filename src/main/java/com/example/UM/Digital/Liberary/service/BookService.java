package com.example.UM.Digital.Liberary.service;

import com.example.UM.Digital.Liberary.entity.BookEntity;
import com.example.UM.Digital.Liberary.repository.jpa.BookJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class BookService {
    @Autowired
    private BookJpaRepository bookJpaRepository;
    public BookEntity addBook(BookEntity bookEntity) {
        Optional<BookEntity> exsistingBook = bookJpaRepository.findByIsbn(bookEntity.getIsbn());
        if(exsistingBook.isPresent()){
            throw new RuntimeException("Book already exist with isbn: " + bookEntity.getIsbn());
        }
        return bookJpaRepository.save(bookEntity);
    }
    public List<BookEntity> getAllBooks(){
        return bookJpaRepository.findAll();
    }
    public BookEntity getBookById(Long id){
        return bookJpaRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found with isbn" + id));
    }
    public BookEntity updateBook(Long id, BookEntity updatedBook){
        BookEntity book = getBookById(id);
        book.setName(updatedBook.getName());
        book.setAuthor(updatedBook.getAuthor());
        book.setIsbn(updatedBook.getIsbn());
        return bookJpaRepository.save(book);
    }
    public String deleteBook(Long id) {
        if (!bookJpaRepository.existsById(id)) {
            return "Book does not exist";
        }
        bookJpaRepository.deleteById(id);
        return "Book deleted successfully";
    }

    public List<BookEntity> searchBookByAuthor(String author) {
        return bookJpaRepository.findByAuthorContainingIgnoreCase(author);
    }

    public List<BookEntity> searchBookByName(String name) {
        return bookJpaRepository.findByNameContainingIgnoreCase(name);
    }
}
