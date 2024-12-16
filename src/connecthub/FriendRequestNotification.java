
package connecthub;


public class FriendRequestNotification extends NotificationDecorator {

    public FriendRequestNotification(Notification notification) {
        super(notification);
    }

    @Override
    public void handleAction(String type, String sender, String message, String speciality){
        
    }
}
