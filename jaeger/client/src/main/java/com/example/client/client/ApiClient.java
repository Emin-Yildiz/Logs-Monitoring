package com.example.client.client;

import com.example.client.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "user-client",url = "http://localhost:8080/user")
public interface ApiClient {

    @GetMapping
    List<User> getAllUser();

    @GetMapping("/{id}")
    User getUserById(@PathVariable UUID id);
}
