package com.example.UM.Digital.Liberary.repository.jpa;

import com.example.UM.Digital.Liberary.entity.BookIssueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookIssueJpaRepository extends JpaRepository<BookIssueEntity,Long> {
    List<BookIssueEntity> findByUserEntityId(Long userId);

    boolean existsByUserEntityIdAndBookEntityId(Long userId, Long bookId);

    long countByUserEntityId(Long userId);

    List<BookIssueEntity> findByUserEntityIdAndReturnedFalse(Long userId);

    long countByUserEntityId(Integer userId);

}
