/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connecthub;

import connecthub.NotificationsDatabase;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author PC
 */
public class BasicNotification implements Notification {

    private User owner;
    private String sender;
    private String message;
    private String type;
    private String status;

    @Override
    public void send(User owner, String sender, String message, String type) {
        System.out.println("Basic notification sent: " + message);
        this.message = message;
        this.owner = owner;
        this.sender = sender;
        this.type = type;
        this.status = "active";
        saveToFile();
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getOwner() {
        return owner;
    }

    public String getType() {
        return type;
    }

    @Override
    public void handleAction(String action) {
        System.out.println("No specific action available for basic notifications.");
    }

    public void saveToFile() {
        NotificationsDatabase db = new NotificationsDatabase();
        JSONArray notificationsArray = db.loadNotifications();

        JSONObject notificationsObj = new JSONObject();
        notificationsObj.put("owner", this.owner.getUserId());
        notificationsObj.put("sender", this.sender);
        notificationsObj.put("message", this.message);
        notificationsObj.put("type", this.type);

        notificationsArray.put(notificationsObj);
        db.saveNotifications(notificationsArray);
    }
}
