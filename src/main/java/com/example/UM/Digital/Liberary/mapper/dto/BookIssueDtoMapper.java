package com.example.UM.Digital.Liberary.mapper.dto;

import com.example.UM.Digital.Liberary.dto.BookIssueDto;
import com.example.UM.Digital.Liberary.entity.BookEntity;
import com.example.UM.Digital.Liberary.entity.BookIssueEntity;
import com.example.UM.Digital.Liberary.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class BookIssueDtoMapper {

    public static BookIssueEntity toEntity(
            BookIssueDto dto,
            UserEntity user,
            BookEntity book,
            LocalDate expiryDate
    ) {

        return BookIssueEntity.builder()
                .userEntity(user)
                .bookEntity(book)
                .issueDate(
                        dto.getIssueDate() != null
                                ? dto.getIssueDate()
                                : LocalDate.now()
                )
                .expiryDate(expiryDate)
                .build();
    }

    public static BookIssueDto toDTO(BookIssueEntity entity) {

        BookIssueDto dto = new BookIssueDto();

        dto.setUserId(entity.getUserEntity().getId());

        dto.setBookId(entity.getBookEntity().getId());

        dto.setIssueDate(entity.getIssueDate());

        dto.setExpiryDate(entity.getExpiryDate());

        return dto;
    }
}