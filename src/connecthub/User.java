package connecthub;

import java.util.*;

public class User extends UserManagement{

    
    private List<User> friends;
    private List<FriendRequest> friendRequests;
    private List<User> blockedUsers;

    public User(String email, String username, String password, String DOB, String profilePhoto, String coverPhoto, String bio) {
        super(email, username, password, DOB, profilePhoto, coverPhoto, bio);
        this.friends = new ArrayList<>();
        this.friendRequests = new ArrayList<>();
        this.blockedUsers = new ArrayList<>();
    }

    // Setters
    public void setPassword(String password) {
        this.password = generatePassword(password);
        database.refresh(this);
    }

    public void setStatus(boolean status) {
        this.status = status;
        database.refresh(this);
    }

    public void setEmail(String email) {
        this.email = email;
        database.refresh(this);
    }

    public void setUsername(String username) {
        this.username = username;
        database.refresh(this);
    }

    public void changeProfilePhoto(String path) {
        this.profilePhoto = path;
        database.refresh(this);
    }

    public void changeCoverPhoto(String path) {
        this.coverPhoto = path;
        database.refresh(this);
    }

    public void changeBio(String bio) {
        this.bio = bio;
        database.refresh(this);
    }
    
    
    // FRIENDS MANAGEMENT PART

    // add or remove a friend
    public void addFriend(User friend) {
        if (!friends.contains(friend) && !blockedUsers.contains(friend)) {
            friends.add(friend);
            database.refresh(this);
        }
    }

    public void removeFriend(User friend) {
        if (friends.contains(friend)) {
            friends.remove(friend);
            database.refresh(this);
        }
    }

    // send or recieve a request
    public void sendFriendRequest(User receiver) {
        FriendRequest request = new FriendRequest(this, receiver);
        receiver.receiveFriendRequest(request);
        database.refresh(receiver);
        
    }

    public void receiveFriendRequest(FriendRequest request) {
        friendRequests.add(request);       
    }

    // accept or decline a request
    public void acceptFriendRequest(FriendRequest request) {
        if (request.getReceiver().equals(this) && request.isPending()) {
            this.getFriendRequests().remove(request);
            this.addFriend(request.getSender());
            request.getSender().addFriend(this);
            request.accept();
        }
    }

    public void declineFriendRequest(FriendRequest request) {
        if (request.getReceiver().equals(this) && request.isPending()) {
            request.decline();
            this.getFriendRequests().remove(request);
            database.refresh(this);
        }
    }

    // block or unblock a user
    public void blockUser(User user) {
        if (!blockedUsers.contains(user)) {
            blockedUsers.add(user);
            this.removeFriend(user);
            user.removeFriend(this);
        }
    }

    public void unblockUser(User user) {
        if (blockedUsers.contains(user)) {
            blockedUsers.remove(user);
            database.refresh(this);
        }
    }

    // check if user is blocked
    public boolean isBlocked(User user) {
        return blockedUsers.contains(user);
    }

    // Getters for: {friends, friend requests, mutual friends, blocked users} lists
    public List<User> getFriends() {
        return friends;
    }

    public List<FriendRequest> getFriendRequests() {
        return friendRequests;
    }

    public List<User> getBlockedUsers() {
        return blockedUsers;
    }

    public List<User> friendsOfFriends() {
        List<User> fof = new ArrayList<>();
        for (User k : this.getFriends()) {
            for (User u : k.getFriends()) {
                if ((!this.getFriends().contains(u)) && (!this.isBlocked(u))) {
                    fof.add(u);
                }
            }
        }
        return fof;
    }

    @Override
    public String toString() {
        return "User{"
                + "userId='" + userId + '\''
                + ", email='" + email + '\''
                + ", username='" + username + '\''
                + ", dateOfBirth='" + DOB + '\''
                + ", status=" + (status ? "Online" : "Offline")
                + ", friends=" + friends
                + '}';
    }

}
