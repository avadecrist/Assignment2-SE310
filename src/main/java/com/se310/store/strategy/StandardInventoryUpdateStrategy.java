package com.se310.store.strategy;

import com.se310.store.model.Inventory;
import com.se310.store.model.StoreException;
import com.se310.store.model.InventoryType; 

/**
 * @author  Sergey L. Sundukovskiy
 * @version 1.0
 * @since   2025-09-25
 */
public class StandardInventoryUpdateStrategy implements InventoryUpdateStrategy {
    //TODO: Implement Strategy Pattern making sure inventory stays in the acceptable bounds for standard products

    @Override
    public void updateQuantity(Inventory inventory, int newCount) throws StoreException {
        if (inventory == null) {
            throw new StoreException(
                "updateQuantity",
                "Inventory cannot be null."
            );
        }

        // Validate inventory type
        InventoryType type = inventory.getType();
        if (type != null && type != InventoryType.standard) {
            throw new StoreException(
                "updateQuantity",
                "StandardInventoryUpdateStrategy can only be applied to STANDARD inventory type." 
            );
        }

        // Business rule checks
        int capacity = inventory.getCapacity();

        if (newCount < 0) {
            throw new StoreException(
                "updateQuantity",
                "Inventory count cannot be negative."
            );
        }

        if (newCount > capacity) {
            throw new StoreException(
                "updateQuantity",
                "Inventory count %d exceeds capacity for STANDARD inventory."
            );
        }

        // Update the inventory count since all checks passed
        inventory.setCount(newCount);
    }
}
