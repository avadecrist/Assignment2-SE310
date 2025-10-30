package com.se310.store.observer;

/**
 * Observer interface for the Observer Pattern
 * Observers are notified when Devices (Observables) change state
 *
 * @author  Sergey L. Sundukovskiy
 * @version 1.0
 * @since   2025-09-25
 */
public interface Observer {

    /**
    * @param deviceId ID of the device that changed state
    * @param eventType Event that occured to change state of device
    * @param message Description of event
    */
    void update(String deviceId, String eventType, String message);


}