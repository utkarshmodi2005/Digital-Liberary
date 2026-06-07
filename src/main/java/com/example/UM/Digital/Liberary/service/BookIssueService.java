package com.example.UM.Digital.Liberary.service;
import com.example.UM.Digital.Liberary.dto.BookIssueDto;
import com.example.UM.Digital.Liberary.entity.BookEntity;
import com.example.UM.Digital.Liberary.entity.BookIssueEntity;
import com.example.UM.Digital.Liberary.entity.UserEntity;
import com.example.UM.Digital.Liberary.enums.SubscriptionType;
import com.example.UM.Digital.Liberary.mapper.dto.BookIssueDtoMapper;
import com.example.UM.Digital.Liberary.repository.jpa.BookIssueJpaRepository;
import com.example.UM.Digital.Liberary.repository.jpa.BookJpaRepository;
import com.example.UM.Digital.Liberary.repository.jpa.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookIssueService {

    @Autowired
    private BookIssueJpaRepository issueRepo;

    @Autowired
    private UserJpaRepository userRepo;

    @Autowired
    private BookJpaRepository bookRepo;

    public BookIssueDto issueBook(BookIssueDto dto) {

        UserEntity user = getUser(dto.getUserId());
        SubscriptionType type = user.getSubscriptionType();
        BookEntity book = getBook(dto.getBookId());
        if (type == SubscriptionType.NOTSUBSCRIBED) {
            throw new RuntimeException(
                    "User does not have subscription"
            );
        }
        LocalDate expiryDate = LocalDate.now();

        if (type == SubscriptionType.PLUS) {
            expiryDate = expiryDate.plusDays(7);
        }

        else if (type == SubscriptionType.PRO) {
            expiryDate = expiryDate.plusDays(14);
        }

        else if (type == SubscriptionType.PREMIUM) {
            expiryDate = expiryDate.plusMonths(1);
        }

        validate(user, book, dto);

        BookIssueEntity issue = BookIssueDtoMapper.toEntity(dto, user, book, expiryDate);

        return BookIssueDtoMapper.toDTO(issueRepo.save(issue));
    }

    public long getIssuedBookCount(Long userId) {

        userRepo.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        return issueRepo.countByUserEntityId(userId);
    }


    private UserEntity getUser(Long userId) {
        return userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private BookEntity getBook(Long bookId) {
        return bookRepo.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    private void validate(UserEntity user, BookEntity book, BookIssueDto dto) {

         if (issueRepo.existsByUserEntityIdAndBookEntityId(dto.getUserId(), dto.getBookId())) {
             throw new RuntimeException("Already issued");
         }

    }

    public BookIssueDto getIssueById(Long id) {
        return BookIssueDtoMapper.toDTO(
                issueRepo.findById(id)
                        .orElseThrow(() -> new RuntimeException("Issue not found"))
        );
    }

    public String returnBook(Long id) {
        BookIssueEntity issue = issueRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Issue not found"));
        issue.setReturned(true);
        issueRepo.save(issue);
        return "Book returned";
    }

    public List<BookIssueDto> getActiveBooks(Long userId) {

        userRepo.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        List<BookIssueEntity> issues =
                issueRepo.findByUserEntityIdAndReturnedFalse(userId);

        return issues.stream()
                .map(BookIssueDtoMapper::toDTO)
                .toList();
    }

    public List<BookIssueDto> getUserIssues(Long userId) {

        userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<BookIssueEntity> issues = issueRepo.findByUserEntityId(userId);

        return issues.stream()
                .map(BookIssueDtoMapper::toDTO)
                .toList();
    }

    public Map<String, Long> getTopBorrowedBooks() {

        List<BookIssueEntity> issues = issueRepo.findAll();

        Map<String, Long> borrowCount = new HashMap<>();

        for (BookIssueEntity issue : issues) {

            String title = issue.getBookEntity().getName();

            borrowCount.put(
                    title,
                    borrowCount.getOrDefault(title, 0L) + 1
            );
        }

        return borrowCount;
    }
}