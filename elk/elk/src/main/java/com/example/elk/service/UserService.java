package com.example.elk.service;

import com.example.elk.domain.User;
import com.example.elk.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Log log = LogFactory.getLog(UserService.class);
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(User user){
        userRepository.save(user);
        log.info("User save");
    }

    public void deleteUserById(Long id){
        userRepository.deleteById(id);
        log.info("delete user from database: ID: " + id);
    }

    public void getAllUser(){
        userRepository.findAll();
        log.info("get all user from database");
    }

}
