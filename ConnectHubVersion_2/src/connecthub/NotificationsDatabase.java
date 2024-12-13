
package connecthub;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;


public class NotificationsDatabase {

    private final String FILE_PATH = "notifications.json";
    private List<BasicNotification> notifications = convertJsonArrayToNotificationsList(loadNotifications());

    // Load groups from file
    public JSONArray loadNotifications() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            StringBuilder contentBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line);
            }
            return new JSONArray(contentBuilder.toString());
        } catch (IOException e) {
            System.out.println("Error loading Notifications: " + e.getMessage());
            return new JSONArray();
        }
    }

    // Save groups to file
    public void saveNotifications(JSONArray contentArray) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            writer.write(contentArray.toString(4));
        } catch (IOException e) {
            System.out.println("Error saving Notifications: " + e.getMessage());
        }
    }
    
    public List<BasicNotification> getAllNotifications(){
        return notifications;
    }
    
    public BasicNotification getNotification(String owner, String sender, String message){
        for (BasicNotification not: notifications){
            if (not.getOwner().equals(owner) && not.getSender().equals(sender) && not.getMessage().equals(message))
                return not;
        }
        return null;
    }
    
    public boolean deleteNotification(BasicNotification not){

        JSONArray notificationsArray = loadNotifications();
        boolean notificationFound = false;

        for (int i = 0; i < notificationsArray.length(); i++) {
            JSONObject notification = notificationsArray.getJSONObject(i);
            if (notification.getString("owner").equals(not.getOwner()) && notification.getString("sender").equals(not.getSender()) && notification.getString("message").equals(not.getMessage())) {
                notificationsArray.remove(i);
                notificationFound = true;
                break;
            }
        }

        if (notificationFound) {
            saveNotifications(notificationsArray);
            return true;
        } else {
            return false;
        }
    }

    //Helping method to convert JsonArray to Group List
    public List<BasicNotification> convertJsonArrayToNotificationsList(JSONArray jsonArray) {
        List<BasicNotification> notificationList = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject notificationJson = jsonArray.getJSONObject(i);

            BasicNotification notification = new BasicNotification();
            notification.setOwner(notificationJson.getString("owner"));
            notification.setSender(notificationJson.getString("sender"));
            notification.setMessage(notificationJson.getString("message"));
            notification.setType(notificationJson.getString("type"));
            notification.setStatus(notificationJson.getString("status"));

            notificationList.add(notification);
        }

        return notificationList;
    }
}
