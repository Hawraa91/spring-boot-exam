package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;

/**
 * ProductController — REST API endpoints for products.
 *
 * YOUR TASK (Part B):
 *   1. Add constructor injection for ProductService
 *   2. Implement all 5 endpoints below
 *   3. Use ResponseEntity to return proper HTTP status codes:
 *      - GET all     → 200 OK
 *      - GET by id   → 200 OK or 404 Not Found
 *      - POST create → 201 Created
 *      - PUT update  → 200 OK or 404 Not Found
 *      - DELETE      → 204 No Content or 404 Not Found
 *
 * Refer to the BookController from Day 2 for the pattern.
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {

    // Declare a private final ProductService field
    private final ProductService productService;

    // Constructor that takes ProductService as parameter
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    /**
     * GET /api/products
     * Return all products with status 200.
     */
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    /**
     * GET /api/products/{id}
     * Return the product with status 200, or 404 if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        // Hint: use .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build())
        return productService.getProductById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * POST /api/products
     * Create a new product. Return it with status 201 Created.
     */
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        // Hint: use ResponseEntity.status(HttpStatus.CREATED).body(...)
        Product createdProduct = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    /**
     * PUT /api/products/{id}
     * Update an existing product. Return 200 with updated product, or 404 if not found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        //Check from my side
        Product updatedProduct = productService.updateProduct(id, product).orElse(null);
        if (updatedProduct != null) {
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * DELETE /api/products/{id}
     * Delete a product. Return 204 No Content if deleted, 404 if not found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        // Hint: return ResponseEntity.noContent().build() for success
        boolean deleted = productService.deleteProduct(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    
    }
}
