
package connecthub;


public abstract class NotificationDecorator implements Notification {

    protected Notification wrappedNotification;

    public NotificationDecorator(Notification notification) {
        this.wrappedNotification = notification != null ? notification : new BasicNotification();
    }

    @Override
    public void send(String owner, String sender, String message, String type) {
        wrappedNotification.send(owner, sender, message, type);
    }

}
