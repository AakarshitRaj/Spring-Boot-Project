package com.ecommerce.productservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.productservice.dto.ProductResponse;
import com.ecommerce.productservice.service.ProductService;


@RestController
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/products")
    public List<ProductResponse> getProducts(){

        return service.getAllProducts();

    }

}