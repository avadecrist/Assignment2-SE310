package com.se310.store.strategy;

import com.se310.store.model.Inventory;
import com.se310.store.model.StoreException;
import com.se310.store.model.InventoryType; 

/**
 * Flexible implementation of the Inventory
 * This strategy allows temporary over-capacity for inventory updates
 * Part of the Strategy Pattern implementation
 *
 * @author  Sergey L. Sundukovskiy
 * @version 1.0
 * @since   2025-09-25
 */
public class FlexibleInventoryUpdateStrategy implements InventoryUpdateStrategy {

    //TODO: Implement Strategy Pattern allowing 20% overcapacity for flexible inventory
    
    @Override
    public void updateQuantity(Inventory inventory, int addedCount) throws StoreException {
        if (inventory == null) {
            throw new StoreException(
                "updateQuantity",
                "Inventory cannot be null."
            );
        }

        // Check inventory type
        InventoryType type = inventory.getType();
        if (type != null && type != InventoryType.flexible) {
            throw new StoreException(
                "updateQuantity",
                "FlexibleInventoryUpdateStrategy can only be applied to FLEXIBLE inventory type."
            );
        }

    
        double flexibleCapacity = inventory.getCapacity() * 1.2; 
        
        int currentCount = inventory.getCount();
        int updatedCount = currentCount + addedCount;

        if (updatedCount < 0) {
            throw new StoreException(
                "updateQuantity",
                "Inventory count cannot be negative."
            );
        }


        if (updatedCount > flexibleCapacity) {
            throw new StoreException(
                "updateQuantity",
                "Inventory count exceeds flexible capacity limit."
            );
        }

        // Update the inventory count since all checks passed
        inventory.setCount(updatedCount);
    }
}