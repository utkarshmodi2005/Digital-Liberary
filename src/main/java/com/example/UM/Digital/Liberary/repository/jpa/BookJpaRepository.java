package com.example.UM.Digital.Liberary.repository.jpa;

import com.example.UM.Digital.Liberary.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookJpaRepository extends JpaRepository<BookEntity,Long> {
    Optional<BookEntity> findByIsbn(String isbn);

    List<BookEntity> findByAuthorContainingIgnoreCase(String author);

    List<BookEntity> findByNameContainingIgnoreCase(String name);
}
