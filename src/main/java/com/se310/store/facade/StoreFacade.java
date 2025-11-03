package com.se310.store.facade;

import com.se310.store.model.Customer;
import com.se310.store.model.CustomerType;
import com.se310.store.model.Inventory;
import com.se310.store.model.InventoryLocation;
import com.se310.store.model.InventoryType;
import com.se310.store.model.Product;
import com.se310.store.model.Store;
import com.se310.store.model.StoreException;
import com.se310.store.model.Temperature;

/**
 * Facade Pattern implementation for the store subsystem
 *
 * @author  Sergey L. Sundukovskiy
 * @version 1.0
 * @since   2025-09-25
 */
public class StoreFacade {

    private Store store; // The core store instance

    // Creates a new Store and initializes its structure
    // Hides all the internal setup logic from the user, like a "facade" should!
    public void createStore(String id, String address, String description) {
        this.store = new Store(id, address, description);
        System.out.println("Store created successfully: " + store.getDescription());
    }

    // Adds a new Customer to the Store, handling all the internal details
    public void addCustomer(String id, String firstName, String lastName, CustomerType type, String email, String accountAddress) {
        ensureStoreExists();

        Customer customer = new Customer(id, firstName, lastName, type, email, accountAddress);
        try {
            store.addCustomer(customer);
            System.out.println("Customer added: " + firstName + " " + lastName);
        } catch (StoreException e) {
            System.err.println("Error adding customer: " + e.getMessage());
        }
    }

    // Adds a new Product to the Store inventory
    public void addProduct(String id, String name, String description, String size, String category, Double price, Temperature temperature, String storeId, String aisleId, String shelfId) {
        // Make sure the store exists so no errors happen
        ensureStoreExists();

        // Uses the given parameters
        Product product = new Product(id, name, description, size, category, price, temperature);

        // Uses the parameters for entering where the location of the product goes
        InventoryLocation location = new InventoryLocation(storeId, aisleId, shelfId); 
        Inventory inventory = new Inventory(
                product.getId(),
                location,
                100,    // capacity
                0,         // quantity
                "Inventory for " + name,
                InventoryType.standard
        );

        // Error handling
        try {
            store.addInventory(inventory);
            System.out.println("Product added: " + name + " ($" + price + ")");
        } catch (StoreException e) {
            System.err.println("Error adding product: " + e.getMessage());
        }
    }

    // Retrieves a Customer by ID
    public Customer findCustomer(String id) {
        ensureStoreExists();
        Customer customer = store.getCustomer(id);
        if (customer != null) {
            System.out.println("Customer found: " + customer.getFirstName());
        } else {
            System.out.println("No customer found with ID: " + id);
        }
        return customer;
    }

    // Retrieves the internal Store object (read-only access if needed)
    public Store getStore() {
        ensureStoreExists();
        return store;
    }

    // Helper method to check that the Store is created before using it
    private void ensureStoreExists() {
        if (this.store == null) {
            throw new IllegalStateException("Store has not been created yet. Please call createStore() first.");
        }
    }

}