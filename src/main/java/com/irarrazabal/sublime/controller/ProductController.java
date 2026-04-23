package com.irarrazabal.sublime.controller;

import com.irarrazabal.sublime.DTO.CreateProductRequest;
import com.irarrazabal.sublime.DTO.ProductResponse;
import com.irarrazabal.sublime.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

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

    @GetMapping
    public List<ProductResponse> getAllProducts(){
    System.out.println("Controller");

    return productService.getAllProducts();
}

    @GetMapping("/{id}")
    public List<ProductResponse> getProduct(@PathVariable  Long id){
        return Collections.singletonList(productService.getById(id));
    }


}
