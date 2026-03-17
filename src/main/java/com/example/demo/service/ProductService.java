package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

/**
 * ProductService — business logic layer for products.
 *
 * YOUR TASK (Part B):
 *   1. Add constructor injection for ProductRepository
 *   2. Implement all 5 methods below
 *
 * Follow the same pattern from the BookService in Day 2:
 *   - Constructor takes the repository as a parameter
 *   - Each method delegates to the repository
 */
@Service
public class ProductService {

    //Declare a private final ProductRepository field
    private final ProductRepository productRepository;


    //Constructor that takes ProductRepository as parameter (constructor injection)
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    /**
     * Get all products.
     */
    public List<Product> getAllProducts() {
        //Delegate to repository
        return productRepository.findAll();

    }

    /**
     * Get a product by ID.
     * Returns Optional<Product> — empty if not found.
     */
    public Optional<Product> getProductById(Long id) {
        //Delegate to repository
        return productRepository.findById(id);
    }

    /**
     * Create a new product.
     * @return the saved product (with generated ID)
     */
    public Product createProduct(Product product) {
        //Delegate to repository
        return productRepository.save(product);
    }

    /**
     * Update an existing product.
     * Find the existing product by ID, update its fields, and save it.
     *
     * @return Optional containing the updated product, or empty if not found
     */
    public Optional<Product> updateProduct(Long id, Product updated) {
        // Find existing product by ID
        // If found, update its name, category, price, and quantity
        // Save and return the updated product
        // If not found, return Optional.empty()

        Optional<Product> existingOpt = productRepository.findById(id);
        if (existingOpt.isPresent()) {
            Product existing = existingOpt.get();
            existing.setName(updated.getName());
            existing.setCategory(updated.getCategory());
            existing.setPrice(updated.getPrice());
            existing.setQuantity(updated.getQuantity());
            return Optional.of(productRepository.save(existing));
        }
        return Optional.empty();
    }

    /**
     * Delete a product by ID.
     * @return true if deleted, false if not found
     */
    public boolean deleteProduct(Long id) {
        //Delegate to repository
        return productRepository.deleteById(id);
    }
}
