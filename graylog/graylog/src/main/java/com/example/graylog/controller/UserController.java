package com.example.graylog.controller;

import com.example.graylog.domain.User;
import com.example.graylog.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers(){
        log.info("All user get is succes");
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id){
        log.info("User found id: " + id);
        return new ResponseEntity<>(userService.findUserById(id),HttpStatus.FOUND);
    }

    @PostMapping()
    public ResponseEntity<User> addUser(@RequestBody User user){
        log.info("User was successfully saved User: " + user);
        return new ResponseEntity<>(userService.addUser(user),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> userUpdateById(@RequestBody User user, @PathVariable UUID id){
        log.info("User was successfully updated User: " + user);
        return new ResponseEntity<>(userService.updateUser(user,id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id){
        log.info("User was successfully deleted id: " + id);
        userService.deleteUserById(id);
    }
}
