/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connecthub;

import org.json.JSONArray;
import org.json.JSONObject;

public abstract class NotificationDecorator implements Notification {

    protected Notification wrappedNotification;

    public NotificationDecorator(Notification notification) {
        this.wrappedNotification = notification;
    }

    @Override
    public void send(User owner, String sender, String message, String type) {
        wrappedNotification.send(owner, sender, message, type);
    }

    @Override
    public void handleAction(String action) {
        wrappedNotification.handleAction(action);
    }
}
