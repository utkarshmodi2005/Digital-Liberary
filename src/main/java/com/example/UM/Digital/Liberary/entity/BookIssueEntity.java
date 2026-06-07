package com.example.UM.Digital.Liberary.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book_issue")
public class BookIssueEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id",nullable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_entity_id")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "book_entity_id")
    private BookEntity bookEntity;

    @Column(name = "returned")
    private Boolean returned = false;

    @Column(name = "issue_date", nullable = false)
    private LocalDate issueDate = LocalDate.now();

    @Column(name = "expiry_date", nullable = false)
    private LocalDate expiryDate;

}
