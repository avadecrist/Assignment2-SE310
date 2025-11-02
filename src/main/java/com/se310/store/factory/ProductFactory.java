package com.se310.store.factory;

import com.se310.store.model.Product;
import com.se310.store.model.Temperature;

// Import the new class created, for enum
import main.java.com.se310.store.factory.ProductType;

/**
 * Factory Pattern implementation for creating Product objects
 *
 * @author  Sergey L. Sundukovskiy
 * @version 1.0
 * @since   2025-09-25
 */
public class ProductFactory {

    //TODO: Implement Factory Pattern for creating discounted, premium and standard products

    // make products, dont implement
    // future extension, new types for future use



    /**
     * Creates a Product of the given ProductType with custom rules for pricing and description.
     *
     * @param id            Product ID
     * @param name          Product name
     * @param description   Base description
     * @param size          Product size
     * @param category      Product category
     * @param basePrice     Product base price before adjustments
     * @param temperature   Product storage temperature
     * @param type          The type of product to create (STANDARD, DISCOUNTED, PREMIUM)
     * @return A Product object customized according to its type
     */
    public static Product createProduct(String id, String name, String description, String size, String category, double basePrice, Temperature temperature, ProductType type) {
        double adjustedPrice = basePrice;
        String adjustedDescription = description;

        // Switch case to indicate the type of product
        switch (type) {
            // Discounted
            case DISCOUNTED:
                adjustedPrice = basePrice * 0.85;  // 15% off
                adjustedDescription = description + " (Discounted)";
                break;

            // Premium
            case PREMIUM:
                adjustedPrice = basePrice * 1.25;  // 25% markup
                adjustedDescription = description + " (Premium Quality)";
                break;

            // and Standard!
            case STANDARD:
            default:
                // No changes for standard product
                break;
        }

        // This returns the new product with the adjustments, and uses the parameters to fill the other spaces
        return new Product(id, name, adjustedDescription, size, category, adjustedPrice, temperature);
    }



}