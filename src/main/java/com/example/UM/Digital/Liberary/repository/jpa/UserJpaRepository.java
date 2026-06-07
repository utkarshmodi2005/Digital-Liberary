package com.example.UM.Digital.Liberary.repository.jpa;

import com.example.UM.Digital.Liberary.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByEmail(String email);
}
