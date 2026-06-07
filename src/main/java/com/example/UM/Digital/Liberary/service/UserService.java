package com.example.UM.Digital.Liberary.service;

import com.example.UM.Digital.Liberary.entity.UserEntity;
import com.example.UM.Digital.Liberary.repository.jpa.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserJpaRepository userJpaRepository;
    public UserEntity addUser(UserEntity user){
        if(userJpaRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already Exists");
        }
        return userJpaRepository.save(user);
    }
    public List<UserEntity> getAllUsers() {
        return userJpaRepository.findAll();
    }

    public UserEntity getUserById(Long id) {
        return userJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
    public UserEntity updateUser(Long id, UserEntity updatedUser) {
        UserEntity user = getUserById(id);
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setEmail(updatedUser.getEmail());
        user.setId(updatedUser.getId());
        user.setSubscriptionType(updatedUser.getSubscriptionType());
        return userJpaRepository.save(user);
    }
    public String deleteUser(Long id) {
        Optional<UserEntity> user = userJpaRepository.findById(id);

        if (user.isPresent()) {
            userJpaRepository.deleteById(id);
            return "User deleted successfully";
        } else {
            return "User does not exist";
        }
    }
}
