package com.se310.store.strategy;

import com.se310.store.model.Inventory;
import com.se310.store.model.StoreException;
import com.se310.store.model.InventoryType;

public interface InventoryUpdateStrategy {

    /**
     * Set the absolute quantity for the given inventory (i.e. after recount).
     *
     * @param inventory 
     * @param newQuantity 
     * @throws StoreException 
     */
    void updateQuantity(Inventory inventory, int newQuantity) throws StoreException;
}