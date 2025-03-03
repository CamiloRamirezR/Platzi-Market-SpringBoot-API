package com.platzi.market.web.controller;

import com.platzi.market.domain.Product;
import com.platzi.market.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAll(){
        return productService.getAll();
    }

    @GetMapping("/{Id}")
    public Optional<Product> getProduct(@PathVariable("Id") int productId){
        return productService.getProduct(productId);
    }

    @GetMapping("/category/{Id}")
    public Optional<List<Product>> getByCategory(@PathVariable("Id") int categoryId){
        return productService.getByCategory(categoryId);
    }

    @PostMapping
    public Product save(@RequestBody Product product) {
        return productService.save(product);
    }

    @DeleteMapping("/delete/{Id}")
    public boolean delete(@PathVariable("Id") int productId) {
        return productService.delete(productId);
    }
}
