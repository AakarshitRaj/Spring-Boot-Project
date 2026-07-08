package com.ecommerce.productservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ecommerce.productservice.dto.ProductResponse;
import com.ecommerce.productservice.repository.ProductRepository;


@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<ProductResponse> getAllProducts() {

        return repository.findAll()

                .stream()

                .map(product ->

                        new ProductResponse(
                                product.getId(),
                                product.getName(),
                                product.getPrice()))

                .collect(Collectors.toList());

    }

}