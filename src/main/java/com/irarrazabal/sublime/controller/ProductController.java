package com.irarrazabal.sublime.controller;

import com.irarrazabal.sublime.DTO.CreateProductRequest;
import com.irarrazabal.sublime.DTO.ProductResponse;
import com.irarrazabal.sublime.service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
@PostMapping
    public ProductResponse createProduct(@RequestBody CreateProductRequest request) {
        System.out.println(request);
        System.out.println("Controller");
        return productService.createProduct(request);
    }
}
