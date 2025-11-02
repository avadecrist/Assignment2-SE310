package com.se310.store.factory;

import com.se310.store.model.Customer;
import com.se310.store.model.CustomerType;

/**
 * Factory Pattern implementation for creating Customer objects
 *
 * @author  Sergey L. Sundukovskiy
 * @version 1.0
 * @since   2025-09-25
 */
public class CustomerFactory {


    // CustomerFactory for guests/unregistered customers
    // Creates a Guest Customer with minimal information
    // Guest customers may not have a registered account, so there is no account address
    public static Customer createGuestCustomer(String id, String firstName, String lastName, String email) {
        return new Customer(
                id,
                firstName,
                lastName,
                CustomerType.guest,   // uusing the enum given in model files
                email,
                null                  // no account address for guests, so its null!
        );
    }

    // CustomerFactory for registered customers,
    // Creates a registered customer with full details
    public static Customer createRegisteredCustomer(String id, String firstName, String lastName, String email, String accountAddress) {
        return new Customer(
                id,
                firstName,
                lastName,
                CustomerType.registered,   // using the enum given in model files
                email,
                accountAddress      // account address exists!
        );
    }

}