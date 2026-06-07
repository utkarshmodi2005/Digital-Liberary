package com.example.UM.Digital.Liberary.controller;

import com.example.UM.Digital.Liberary.dto.BookIssueDto;
import com.example.UM.Digital.Liberary.service.BookIssueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/book-issue")
public class BookIssueController {

    @Autowired
    private BookIssueService service;

    @PostMapping
    public BookIssueDto issueBook(@RequestBody BookIssueDto dto) {
        return service.issueBook(dto);
    }

    @GetMapping("/{id}")
    public BookIssueDto getIssue(@PathVariable Long id) {
        return service.getIssueById(id);
    }

    @GetMapping("/user/{userId}")
    public List<BookIssueDto> getUserIssues(@PathVariable Long userId) {
        return service.getUserIssues(userId);
    }

    @GetMapping("/count/{userId}")
    public long getIssuedBookCount(@PathVariable Long userId){
        return service.getIssuedBookCount(userId);
    }

    @GetMapping("/active/{userId}")
    public List<BookIssueDto> getActiveBooks(
            @PathVariable Long userId
    ) {

        return service.getActiveBooks(userId);
    }

    @GetMapping("/top-borrowed")
    public Map<String, Long> getTopBorrowedBooks() {
        return service.getTopBorrowedBooks();
    }

    @DeleteMapping("/{id}")
    public String returnBook(@PathVariable Long id) {
        return service.returnBook(id);
    }
}
