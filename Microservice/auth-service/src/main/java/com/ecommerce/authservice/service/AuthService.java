package com.ecommerce.authservice.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ecommerce.authservice.dto.ProductResponse;


@Service
public class AuthService {

    private final RestTemplate restTemplate;

    public AuthService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<ProductResponse> getProducts() {

        return restTemplate.exchange(
                "http://localhost:8082/products",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ProductResponse>>() {})
                .getBody();

    }

}