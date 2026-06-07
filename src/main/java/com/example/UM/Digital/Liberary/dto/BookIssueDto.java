package com.example.UM.Digital.Liberary.dto;

import lombok.Data;
import tools.jackson.databind.annotation.JsonDeserialize;
import tools.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDate;

@JsonSerialize
@JsonDeserialize

@Data
public class BookIssueDto {
    private Long userId;
    private Long bookId;
    private LocalDate issueDate = LocalDate.now();
    private LocalDate expiryDate;
}
