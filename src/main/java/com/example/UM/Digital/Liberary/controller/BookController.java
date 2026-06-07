package com.example.UM.Digital.Liberary.controller;

import com.example.UM.Digital.Liberary.entity.BookEntity;
import com.example.UM.Digital.Liberary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;
    @PostMapping
    public BookEntity addBook(@RequestBody BookEntity book){
        return bookService.addBook(book);
    }
    @GetMapping
    public List<BookEntity> getAllBooks(){
        return bookService.getAllBooks();
    }
    @GetMapping("/{id}")
    public BookEntity getBookById(@PathVariable Long id){
        return bookService.getBookById(id);
    }
    @PutMapping("/{id}")
    public BookEntity updateBook(@PathVariable Long id, @RequestBody BookEntity book){
        return bookService.updateBook(id,book);
    }
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id){
        return bookService.deleteBook(id);
    }
    @GetMapping("/author/{author}")
    public List<BookEntity> getBookByAuthor(@PathVariable String author){
        return bookService.searchBookByAuthor(author);
    }
    @GetMapping("/name/{name}")
    public List<BookEntity> getBookByName(@PathVariable String name){
        return bookService.searchBookByName(name);
    }
}
