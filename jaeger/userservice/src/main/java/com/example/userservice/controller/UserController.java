package com.example.userservice.controller;

import com.example.userservice.domain.User;
import com.example.userservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUserrs(){
        return ResponseEntity.ok(userService.getAllUser());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping()
    public ResponseEntity<User> addUser(@RequestBody User user){
        return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUserById(@RequestBody User user, @PathVariable UUID id){
        return ResponseEntity.ok(userService.updateUser(user,id));
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable UUID id){
        userService.deleteUserById(id);
    }
}
