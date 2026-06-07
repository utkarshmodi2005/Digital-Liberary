package com.example.UM.Digital.Liberary.controller;

import com.example.UM.Digital.Liberary.entity.UserEntity;
import com.example.UM.Digital.Liberary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody UserEntity user){
        try {
            return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping
    public List<UserEntity> getAllUsers(){
        return userService.getAllUsers();
    }
    @GetMapping("/{id}")
    public UserEntity getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }
    @PutMapping("/{id}")
    public UserEntity updateUser(@PathVariable Long id, @RequestBody UserEntity user){
        return userService.updateUser(id, user);
    }
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }
}
