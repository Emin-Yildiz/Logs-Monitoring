package com.example.graylog.services;

import com.example.graylog.domain.User;
import com.example.graylog.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ListResourceBundle;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user){
        return userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User findUserById(UUID id){
        Optional<User> foundUser = userRepository.findById(id);
        return foundUser.orElse(null);
    }

    public User updateUser(User user, UUID id){
        Optional<User> foundUser = userRepository.findById(id);
        if (foundUser.isPresent()){
            User user1 = foundUser.get();
            user1.setUsername(user.getUsername());
            user1.setPassword(user.getPassword());
            return userRepository.save(user1);
        }else {
            return null;
        }
    }

    public void deleteUserById(UUID id){
        userRepository.deleteById(id);
    }
}
