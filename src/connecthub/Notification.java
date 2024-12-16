
package connecthub;


public interface Notification {
    void send(String owner, String sender, String message, String type);
    public void handleAction(String type, String sender, String message, String speciality);
}
