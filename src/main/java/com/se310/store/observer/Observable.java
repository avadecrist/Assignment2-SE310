package com.se310.store.observer;

/**
 * Observable interface for the Observer Pattern
 * Devices implement this interface to allow observers to subscribe to their state changes
 *
 *
 * @author  Sergey L. Sundukovskiy
 * @version 1.0
 * @since   2025-09-25
 */
public interface Observable {
    
    // Observer Registration
    /**
     * @param observer The observer to register
     */
    void registerObserver(Observer observer);

    // Observer Removal
    /**
     * @param observer The observer to remove
     */
    void removeObserver(Observer observer);

    // Observer Notification
    /**
     * @param eventType Event that occured to change state of device
     * @param message Description of event
     */
    void notifyObservers(String eventType, String message);

}