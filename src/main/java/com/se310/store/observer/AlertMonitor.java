package com.se310.store.observer;

/**
 * Concrete Observer that monitors for critical events and alerts
 *
 *
 * @author  Sergey L. Sundukovskiy
 * @version 1.0
 * @since   2025-09-25
 */
public class AlertMonitor implements Observer {

    //TODO: Implement Alert Monitor that prints out critical events to the console
    public void update(String deviceId, String eventType, String message) { 

        String combined = ((eventType == null ? "":eventType) + " " + (message == null ? "" : message)).toLowerCase();
        if (combined.contains("error") ||combined.contains("fail") || combined.contains("critical") || combined.contains("warning") ||
            combined.contains("overheat")||combined.contains("offline")||combined.contains("spill")||combined.contains("leak")||combined.contains("crash")) {

            System.out.println("[ALERT] AlertMonitor received: eventType="+eventType +", message="+message);
        }
    }

} 