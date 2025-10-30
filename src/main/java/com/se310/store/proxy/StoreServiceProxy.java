package com.se310.store.proxy;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.se310.store.singleton.StoreService;
import com.se310.store.model.*; 

/**
 * Proxy Pattern implementation for the StoreService
 * The Proxy Pattern provides a surrogate or placeholder for another object to control access to it
 * This implementation adds access control before delegating to the real StoreService
 *
 * @author  Sergey L. Sundukovskiy
 * @version 1.0
 * @since   2025-09-25
 */
public class StoreServiceProxy {

    //TODO: Implement Proxy Pattern allowing command execution only with a valid token
    
    // Make the proxy itself a singleton as well
    private static StoreServiceProxy instance;

    /**
     * Accessor for the proxy singleton
     */
    public static StoreServiceProxy getInstance() {
        StoreServiceProxy result = instance;
        if (result == null) {
            synchronized (StoreServiceProxy.class) {
                result = instance;
                if (result == null) {
                    result = new StoreServiceProxy();
                    instance = result;
                    System.out.println(">>> StoreServiceProxy instance created.");
                }
            }
        }
        return result;
    }

    // Reference to the real subject
    private final StoreService real = StoreService.getInstance();

    private StoreServiceProxy() { } // private constructor

    // Set of valid tokens
    private final Set<String> validTokens = Collections.synchronizedSet(new HashSet<>());


    /* ------------------------- Token management API ------------------------- */

    /**
     * Register a token.
     */
    public void registerToken(String token) {
        if (token != null && !token.isBlank()) {
            validTokens.add(token);
        }
    }

    /**
     * Revoke a previously registered token.
     */
    public void revokeToken(String token) {
        if (token != null) {
            validTokens.remove(token);
        }
    }

    /**
     * Basic guard to ensure a request is authorized before delegating.
     */
    private void assertAuthorized(String token, String action) throws StoreException {
        if (token == null || token.isBlank() || !validTokens.contains(token)) {
            throw new StoreException(action, "Unauthorized: invalid or missing token");
        }
    }

    /* ---------------------- Proxied operations -------------------- */

    public Store provisionStore(String storeId, String name, String address, String token)
            throws StoreException {
        assertAuthorized(token, "Provision Store");
        return real.provisionStore(storeId, name, address, token);
    }

    public Store showStore(String storeId, String token) throws StoreException {
        assertAuthorized(token, "Show Store");
        return real.showStore(storeId, token);
    }

    public Aisle provisionAisle(String storeId, String aisleNumber, String name, String description,
                                AisleLocation location, String token) throws StoreException {
        assertAuthorized(token, "Provision Aisle");
        return real.provisionAisle(storeId, aisleNumber, name, description, location, token);
    }  

    public Aisle showAisle(String storeId,  String aisleNumber, String token) throws StoreException {
        assertAuthorized(token, "Show Aisle");
        return real.showAisle(storeId, aisleNumber, token);
    }

    public Shelf provisionShelf(String storeId, String aisleNumber, String shelfId, String name,
                                ShelfLevel level, String description, Temperature temperature, String token) throws StoreException {
        assertAuthorized(token, "Provision Shelf");
        return real.provisionShelf(storeId, aisleNumber, shelfId, name, level, description, temperature, token);
    }

    public Shelf showShelf(String storeId, String aisleNumber, String shelfId, String token) throws StoreException {
        assertAuthorized(token, "Show Shelf");
        return real.showShelf(storeId, aisleNumber, shelfId, token);
    }

    public Inventory provisionInventory(String inventoryId, String storeId, String aisleNumber, String shelfId,
                                        int capacity, int count, String productId, InventoryType type, String token) throws StoreException {
        assertAuthorized(token, "Provision Inventory");
        return real.provisionInventory(inventoryId, storeId, aisleNumber, shelfId, capacity, count, productId, type, token);
    }

    public Inventory showInventory(String inventoryId, String token) throws StoreException {
        assertAuthorized(token, "Show Inventory");
        return real.showInventory(inventoryId, token);
    }

    public Inventory updateInventory(String inventoryId, int count, String token) throws StoreException {
        assertAuthorized(token, "Update Inventory");
        return real.updateInventory(inventoryId, count, token);
    }

    public Product provisionProduct(String productId, String name, String description, String size, String category,
                                    double price, Temperature temperature, String token) throws StoreException {
        assertAuthorized(token, "Provision Product");
        return real.provisionProduct(productId, name, description, size, category, price, temperature, token);
    }

    public Product showProduct(String productId, String token) throws StoreException {
        assertAuthorized(token, "Show Product");
        return real.showProduct(productId, token);
    }

    public Customer provisionCustomer(String customerId, String firstName, String lastName,
                                      CustomerType type, String email, String address, String token) throws StoreException {
        assertAuthorized(token, "Provision Customer");
        return real.provisionCustomer(customerId, firstName, lastName, type, email, address, token);
    }

    public Customer updateCustomer(String customerId, String storeId, String aisleNumber, String token) throws StoreException {
        assertAuthorized(token, "Update Customer");
        return real.updateCustomer(customerId, storeId, aisleNumber, token);
    }

    public Customer showCustomer(String customerId, String token) throws StoreException {
        assertAuthorized(token, "Show Customer");
        return real.showCustomer(customerId, token);
    }

    public Basket provisionBasket(String basketId, String token) throws StoreException {
        assertAuthorized(token, "Provision Basket");
        return real.provisionBasket(basketId, token);
    }

    public Basket assignCustomerBasket(String customerId, String basketId, String token) throws StoreException {
        assertAuthorized(token, "Assign Customer Basket");
        return real.assignCustomerBasket(customerId, basketId, token);
    }

    public Basket getCustomerBasket(String customerId, String token) throws StoreException {
        assertAuthorized(token, "Get Customer Basket");
        return real.getCustomerBasket(customerId, token);
    }

    public Basket addBasketProduct(String basketId, String productId, int count, String token)
            throws StoreException {
        assertAuthorized(token, "Add Basket Product");
        return real.addBasketProduct(basketId, productId, count, token);
    }

    public Basket removeBasketProduct(String basketId, String productId, int count, String token) throws StoreException {
        assertAuthorized(token, "Remove Basket Product");
        return real.removeBasketProduct(basketId, productId, count, token);
    }

    public Basket clearBasket(String basketId, String token) throws StoreException {
        assertAuthorized(token, "Clear Basket");
        return real.clearBasket(basketId, token);
    }

    public Basket showBasket(String basketId, String token) throws StoreException {
        assertAuthorized(token, "Show Basket");
        return real.showBasket(basketId, token);
    }

    public Device provisionDevice(String deviceId, String name, String deviceType, String storeId,
                                  String aisleNumber, String token) throws StoreException {
        assertAuthorized(token, "Provision Device");
        return real.provisionDevice(deviceId, name, deviceType, storeId, aisleNumber, token);
    }

    public Device showDevice(String deviceId, String token) throws StoreException {
        assertAuthorized(token, "Show Device");
        return real.showDevice(deviceId, token);
    }

    public void raiseEvent(String deviceId, String event, String token) throws StoreException {
        assertAuthorized(token, "Raise Event");
        real.raiseEvent(deviceId, event, token);
    }

    public void issueCommand(String deviceId, String command, String token) throws StoreException {
        assertAuthorized(token, "Issue Command");
        real.issueCommand(deviceId, command, token);
    }

}
