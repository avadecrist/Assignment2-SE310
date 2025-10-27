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
    public void updateQuantity(Inventory inventory, int newCount) throws StoreException {
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

        int capacity = inventory.getCapacity();

        if (newCount < 0) {
            throw new StoreException(
                "updateQuantity",
                "Inventory count cannot be negative."
            );
        }

        // Calculate 120% of capacity (20% overcapacity)
        double allowedMax = capacity * 1.2;

        if (newCount > allowedMax) {
            throw new StoreException(
                "updateQuantity",
                String.format(
                    "Inventory count %d exceeds flexible capacity limit (max %.0f).",
                    newCount, allowedMax
                )
            );
        }

        // Update the inventory count since all checks passed
        inventory.setCount(newCount);
    }
}