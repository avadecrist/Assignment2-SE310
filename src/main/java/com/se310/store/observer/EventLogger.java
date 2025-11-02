package com.se310.store.observer;

/**
 * Concrete Observer that logs device events to console
 *
 * @author  Sergey L. Sundukovskiy
 * @version 1.0
 * @since   2025-09-25
 */
public class EventLogger implements Observer {

    //TODO: Implement Event Logger to log device events to console
    @Override
    public void update(String deviceId, String eventType, String message) {
        if (message == null && eventType == null){
            return;
        }
        System.out.println("[EventLogger] Device "+deviceId +" | Event: "+eventType + " | Message: "+message);
    }
}