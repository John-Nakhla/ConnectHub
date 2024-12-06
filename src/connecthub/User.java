
package connecthub;

import java.security.MessageDigest;
import java.util.*;

public class User {
    private final String userId;
    private String email;
    private String username;
    private final String password;
    private final String DOB;
    private boolean status;
    private String profilePhoto;
    private String coverPhoto;
    private String bio;
    private final List<User> friends;
    private final List<FriendRequest> friendRequests;
    private final List<User> blockedUsers; 
    private static int users_count=10000;

    public User(String email, String username, String password, String DOB, String profilePhoto, String coverPhoto, String bio) {
        this.userId = UniqueId();
        this.email = email;
        this.username = username;
        this.password = generatePassword(password);
        this.DOB = DOB;
        this.profilePhoto = profilePhoto;
        this.coverPhoto = coverPhoto;
        this.bio = bio;
        this.status = false;
        this.friends = new ArrayList<>();
        this.friendRequests = new ArrayList<>();
        this.blockedUsers = new ArrayList<>();
    }    
    
    private String UniqueId(){
        users_count++; 
        return "User" + users_count;
    }
    
    // generates hashed password to save in the file
    private String generatePassword(String password){
        try {
            MessageDigest message = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = message.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
    
    // checks a password: used in login
    public boolean checkPassword(String password) {
        return this.password.equals(generatePassword(password));
    }
    
    // Getters
    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getDOB() {
        return DOB;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public String getCoverPhoto() {
        return coverPhoto;
    }

    public String getBio() {
        return bio;
    }

    public String getPassword() {
        return password;
    }   
    
    public boolean isStatus() {
        return status;
    }

    
    // Setters
    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void changeProfilePhoto(String path)
    {
        this.profilePhoto = path;
    }
    public void changeCoverPhoto(String path)
    {
        this.coverPhoto = path;
    }
    public void changeBio(String bio)
    {
        this.bio = bio;
    }
    // FRIENDS MANAGEMENT PART

    // add or remove a friend
    public void addFriend(User friend) {
        if (!friends.contains(friend) && !blockedUsers.contains(friend)) {
            friends.add(friend);
        }
    }
    public void removeFriend(User friend) {
        if(friends.contains(friend)){
            friends.remove(friend);
            }
    }
    
    
    // send or recieve a request
    public void sendFriendRequest(User receiver) {
        FriendRequest request = new FriendRequest(this, receiver);
        receiver.receiveFriendRequest(request);
    }

    public void receiveFriendRequest(FriendRequest request) {
        friendRequests.add(request);
    }


    // accept or decline a request
    public void acceptFriendRequest(FriendRequest request) {
        if (request.getReceiver().equals(this) && request.isPending()) {
            friends.add(request.getSender());
            request.getSender().friends.add(this);
            request.accept();
        }
    }

    public void declineFriendRequest(FriendRequest request) {
        if (request.getReceiver().equals(this) && request.isPending()) {
            request.decline();
        }
    }

    
    // block or unblock a user
    public void blockUser(User user) {
        if (!blockedUsers.contains(user)) {
            blockedUsers.add(user);
            friends.remove(user); 
        }
    }

    public void unblockUser(User user) {
        if(blockedUsers.contains(user))
        blockedUsers.remove(user);
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

    public List<User> friendsOfFriends()
    {
        List<User> fof = new ArrayList<>();
        for(User k : this.getFriends())
        {
            for(User u : k.getFriends())
            {
                if((!this.getFriends().contains(u))&&(!this.isBlocked(u)))
                    fof.add(u);
            }
        }
        return fof;
    }    
    
    
    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", dateOfBirth='" + DOB + '\'' +
                ", status=" + (status ? "Online" : "Offline") +
                ", friends=" + friends +
                '}';
    }

}
