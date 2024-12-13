
package connecthub;

public class FriendRequest {
    private final User sender;
    private final User receiver;
    private String status;

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
    }

    public void decline() {
        this.status = "Declined";
    }

    // checks if a request is pending
    public boolean isPending() {
        return this.status.equals("Pending");
    }
}
