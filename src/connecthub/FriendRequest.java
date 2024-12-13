
package connecthub;

public class FriendRequest {
    private final User sender;
    private final User receiver;
    private String status;
    Notification notification;
    Notification friendRequest = new FriendRequestNotification(notification);
    NotificationsDatabase db = new NotificationsDatabase();
    
    public FriendRequest(User sender, User receiver) {
        this.sender = sender;
        this.receiver = receiver;
        this.status = "Pending";

    }

    // Getters
    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public String getStatus() {
        return status;
    }

    // accepting or declining a request
    public void accept() {
        this.status = "Accepted";
        BasicNotification not = db.getNotification(this.receiver.getUserId(), this.sender.getUserId(), this.sender.getUsername() + " sent you a friend request");
        not.setStatus("Inactive");
        not.saveToFile();
    }

    public void decline() {
        this.status = "Declined";
        BasicNotification not = db.getNotification(this.receiver.getUserId(), this.sender.getUserId(), this.sender.getUsername() + " sent you a friend request");
        not.setStatus("Inactive");
        not.saveToFile();
    }

    // checks if a request is pending
    public boolean isPending() {
        return this.status.equals("Pending");
    }
    
    public void sendNotification(){
        
        switch (this.status) {
            case "Accepted" -> friendRequest.send(sender.getUserId(), receiver.getUserId(), this.receiver.getUsername() + " accepted your friend request", "FR");
            case "Declined" -> friendRequest.send(sender.getUserId(), receiver.getUserId(), this.receiver.getUsername() + " declined your friend request", "FR");
            default -> friendRequest.send(receiver.getUserId(), sender.getUserId(), this.sender.getUsername() + " sent you a friend request", "FR");
        }
        
    }
}
