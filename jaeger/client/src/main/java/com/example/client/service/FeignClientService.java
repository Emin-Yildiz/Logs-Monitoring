package com.example.client.service;

import com.example.client.client.ApiClient;
import com.example.client.domain.User;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FeignClientService {

    private final ApiClient apiClient;
    private final Tracer tracer;

    Logger logger = LoggerFactory.getLogger(FeignClientService.class);

    public FeignClientService(ApiClient apiClient, Tracer tracer) {
        this.apiClient = apiClient;
        this.tracer = tracer;
    }

    public List<User> getAllUser(){
        Span span = tracer.spanBuilder("feign-client-service-create-span").startSpan();
        span.addEvent("Get all users");
        span.end();
        logger.info("Get all users");
        return apiClient.getAllUser();
    }

    public User getUserById(UUID id){
        Span span = tracer.spanBuilder("feign-client-service-create-span").startSpan();
        span.addEvent("Get users by id. ID: " + id);
        span.end();
        logger.info("Get users by id. ID: " + id);
        return apiClient.getUserById(id);
    }
}
