package com.example.userservice.service;

import com.example.userservice.domain.User;
import com.example.userservice.repository.UserRepository;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final Tracer tracer;

    Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository, Tracer tracer) {
        this.userRepository = userRepository;
        this.tracer = tracer;
    }

    public User addUser(User user){
        Span span = tracer.spanBuilder("user-service-create-span").startSpan();
        span.addEvent("User added databases");
        span.end();
        logger.info("User added databases");
        return userRepository.save(user);
    }

    public User updateUser(User user, UUID id){
        Optional<User> foundUser = userRepository.findById(id);
        Span span = tracer.spanBuilder("user-service-create-span").startSpan();
        if (foundUser.isPresent()){
            User newUser = foundUser.get();
            newUser.setUserName(user.getUserName());
            newUser.setPassword(user.getPassword());
            newUser.setEmail(user.getEmail());
            span.addEvent("User updated by ID: " + id);
            span.end();
            logger.info("User updated by ID: " + id);
            return userRepository.save(user);
        }else {
            span.addEvent("User is not found!! ID: " + id);
            span.end();
            logger.info("User is not found!! ID: " + id);
            return null;
        }
    }

    public List<User> getAllUser(){
        Span span = tracer.spanBuilder("user-service-create-span").startSpan();
        span.addEvent("Get all users");
        span.end();
        logger.info("Get all users");
        return userRepository.findAll();
    }

    public User getUserById(UUID id){
        Optional<User> foundUser = userRepository.findById(id);
        Span span = tracer.spanBuilder("user-service-create-span").startSpan();
        if(foundUser.isPresent()){
            span.addEvent("User get by ID: " + id);

            logger.info("Get user by ID: " + id);
            span.end();
            return foundUser.get();
        }else {
            span.addEvent("User not found from db. ID: " + id);
            logger.info("User not found from db. ID: " + id);
            span.end();
            return null;
        }
    }

    public void deleteUserById(UUID id){
        Optional<User> foundUser = userRepository.findById(id);
        Span span = tracer.spanBuilder("user-service-create-span").startSpan();
        if(foundUser.isPresent()){
            logger.info("User is deleted. ID: " + id);
            span.addEvent("User is deleted. ID: " + id);
            userRepository.deleteById(id);
            span.end();
        }else {
            span.addEvent("User is not deleted. User not found. ID: " + id);
            span.end();
            logger.info("User is not deleted. User not found. ID: " + id);
        }
    }
}
