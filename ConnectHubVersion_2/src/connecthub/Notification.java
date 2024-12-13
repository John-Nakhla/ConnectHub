
package connecthub;


public interface Notification {
    void send(String owner, String sender, String message, String type); // To send the notification
}
