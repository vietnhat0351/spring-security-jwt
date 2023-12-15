package com.example.springsecurity.services;

import com.example.springsecurity.entities.Product;
import com.example.springsecurity.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public List<Product> getAllProduct() {
        return repository.findAll();
    }

    public Product getProductById(String id) {
        return repository.findById(id).orElseThrow();
    }

}
