package com.se310.store.observer;

/**
 * Concrete Observer that sends notifications to store management.
 * Implements Singleton pattern to ensure single notification instance per store.
 *
 * @author  Sergey L. Sundukovskiy
 * @version 1.0
 * @since   2025-09-25
 */

public class StoreNotifier implements Observer {

    private static volatile StoreNotifier instance;
    private static final Object lock = new Object();

    // Private constructor for singleton pattern
    private StoreNotifier() {
    }

    // Thread-safe singleton instance getter
    /**
     * @return The singleton StoreNotifier instance
     */
    public static StoreNotifier getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new StoreNotifier();
                }
            }
        }
        return instance;
    }
    
    // update method which is called when there is a change
    /**
     * @param deviceId ID of the device that changed state
     * @param eventType Event that occured to change state of device
     * @param message Description of event
     */
    @Override
    public synchronized void update(String deviceId, String eventType, String message) {
        System.out.println("Store Management Notification");
        System.out.println("Device ID: " + deviceId);
        System.out.println("Event Type: " + eventType);
        System.out.println("Message: " + message);
    }
}