package com.example.client.controller;

import com.example.client.domain.User;
import com.example.client.service.FeignClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/feign")
public class FeignClientController {

    private final FeignClientService feignClientService;

    public FeignClientController(FeignClientService feignClientService) {
        this.feignClientService = feignClientService;
    }

    @GetMapping
    public List<User> getAllUser(){
        return feignClientService.getAllUser();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable UUID id){
        return feignClientService.getUserById(id);
    }
}
