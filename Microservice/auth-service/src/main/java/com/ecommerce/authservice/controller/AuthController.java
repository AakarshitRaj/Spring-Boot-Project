package com.ecommerce.authservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.authservice.dto.ProductResponse;
import com.ecommerce.authservice.service.AuthService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @GetMapping("/auth/products")
    public List<ProductResponse> products() {

        return service.getProducts();

    }

}