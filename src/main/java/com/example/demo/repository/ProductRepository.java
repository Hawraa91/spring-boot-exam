package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Product;

/**
 * ProductRepository — in-memory data store for products.
 * Uses an ArrayList to store products and a counter to generate IDs.
 *
 * YOUR TASK (Part A):
 *   Implement all 5 methods below.
 *
 * IMPORTANT: When saving a NEW product (id is null), assign it a new ID
 * using the nextId counter. When saving an EXISTING product (id is not null),
 * find and replace it in the list.
 */
@Repository
public class ProductRepository {

    private List<Product> products = new ArrayList<>();
    private Long nextId = 1L;

    /**
     * Return all products.
     */
    public List<Product> findAll() {
        // Return the full list of products
        return products;
    }

    /**
     * Find a product by its ID.
     * Loop through the list and return the matching product wrapped in Optional.
     * Return Optional.empty() if not found.
     *
     * Hint: use Optional.of(product) to wrap a found product.
     */
    public Optional<Product> findById(Long id) {
        //Loop through products, find matching ID
        if (id == null) {
            return Optional.empty();
        }

        for (Product product : products) {
            if (id.equals(product.getId())) {
                return Optional.of(product);
            }
        }
        return Optional.empty();
    }

    /**
     * Save a product.
     * - If the product's ID is null → assign a new ID (nextId++) and add to the list
     * - If the product's ID is NOT null → find the existing product and replace it
     *
     * @return the saved product (with ID assigned if new)
     */
    public Product save(Product product) {
        //Implement save logic for both new and existing products
        if (product.getId() == null) {
            // New product
            product.setId(nextId++);
            products.add(product);
        } else {
            // Existing product - find and replace
            for (int i = 0; i < products.size(); i++) {
                if (product.getId().equals(products.get(i).getId())) {
                    products.set(i, product);
                    break;
                }
            }
        }

        return null;
    }

    /**
     * Delete a product by ID.
     * Remove it from the list.
     *
     * @return true if found and deleted, false if not found
     */
    public boolean deleteById(Long id) {
        //Find and remove the product with matching ID
        if(id == null) {
            return false;
        }

        //going through the list to find matching ID and remove it
        for (int i = 0; i < products.size(); i++) {
            if (id.equals(products.get(i).getId())) {
                products.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Check if a product with the given ID exists.
     */
    public boolean existsById(Long id) {
        //Return true if a product with this ID exists in the list
        if (id == null) {
            return false;
        }

        //Going thorugh the list to find matching ID
        for (Product product : products) {
            if (id.equals(product.getId())) {
                return true;
            }
        }
        return false;
    }
}
