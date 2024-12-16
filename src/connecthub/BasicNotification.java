
package connecthub;

import org.json.JSONArray;
import org.json.JSONObject;


public class BasicNotification implements Notification {

    private String owner;
    private String sender;
    private String message;
    private String type;
    private String status;

    @Override
    public void send(String owner, String sender, String message, String type) {
        this.message = message;
        this.owner = owner;
        this.sender = sender;
        this.type = type;
        this.status = "active";
        saveToFile();
    }
    
    @Override
    public void handleAction(String type, String sender, String message, String speciality){
        
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getOwner() {
        return owner;
    }

    public String getType() {
        return type;
    }


    public void saveToFile() {
        NotificationsDatabase db = new NotificationsDatabase();
        db.deleteNotification(this);
        JSONArray notificationsArray = db.loadNotifications();

        JSONObject notificationsObj = new JSONObject();
        notificationsObj.put("owner", this.owner);
        notificationsObj.put("sender", this.sender);
        notificationsObj.put("message", this.message);
        notificationsObj.put("type", this.type);
        notificationsObj.put("status", this.status);

        notificationsArray.put(notificationsObj);
        db.saveNotifications(notificationsArray);
    }
}
