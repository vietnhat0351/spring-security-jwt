package com.example.springsecurity.controllers;

import com.example.springsecurity.entities.Product;
import com.example.springsecurity.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome";
    }

    @GetMapping("/all")
//    @PreAuthorize("hasRole('ADMIN')")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Product> getAllProduct() {
        return service.getAllProduct();
    }

    @GetMapping
    @PreAuthorize("hasAuthority('USER')")
    public Product findById(@RequestParam("id") String id) {
        return service.getProductById(id);
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "Hello admin";
    }

    @GetMapping("/user")
    public String userPage() {
        return "Hello user";
    }


}
