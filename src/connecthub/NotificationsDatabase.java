/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connecthub;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author PC
 */
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

    //Helping method to convert JsonArray to Group List
    public List<BasicNotification> convertJsonArrayToNotificationsList(JSONArray jsonArray) {
        List<BasicNotification> notificationList = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject notificationJson = jsonArray.getJSONObject(i);

            BasicNotification notification = new BasicNotification();
            notification.setMessage(notificationJson.getString("owner"));
            notification.setMessage(notificationJson.getString("sender"));
            notification.setMessage(notificationJson.getString("message"));
            notification.setMessage(notificationJson.getString("type"));

            notificationList.add(notification);
            System.out.println("from load: " + notificationJson.getString("message"));
        }

        return notificationList;
    }
}
