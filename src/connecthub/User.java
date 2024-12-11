package connecthub;

import java.security.MessageDigest;
import java.util.*;

public class User {

    private String userId;
    private String email;
    private String username;
    private String password;
    private String DOB;
    public boolean status;
    private String profilePhoto;
    private String coverPhoto;
    private String bio;
    private List<User> friends;
    private List<FriendRequest> friendRequests;
    private List<User> blockedUsers;
    private static int users_count = 10000;
    private UsersDatabase database = new UsersDatabase();

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

    private String UniqueId() {
        users_count++;
        return "User" + users_count;
    }

    public void setPassword(String password) {
        this.password = generatePassword(password);
        database.refresh(this);
    }

    public void setRealPassword(String password) {
        this.password = password;
    }

    // generates hashed password to save in the file
    private String generatePassword(String password) {
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

    public void setID(String id) {
        this.userId = id;
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
        database.refresh(this);
    }

    public void setEmail(String email) {
        this.email = email;
        database.refresh(this);
    }
    public void setUsername(String name)
    {
        this.username = name;
    }
    public void changeUsername(String username) {
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

    // checks if user is friend
    public boolean isFriend(User u)
    {
       for(User k : this.getFriends())
       {
           if(k.getUserId().equals(u.getUserId()))
           {
               return true;
           }
       }
       return false;
    }
    // add or remove a friend
    public void addFriend(User friend) {
        if (!friends.contains(friend) && !blockedUsers.contains(friend)) {
            friends.add(friend);
            database.refresh(this);
        }
    }

    public void removeFriend(User friend) {
        for(User k : friends)
        {
            if(k.getUserId().equals(friend.getUserId()))
            {
                friends.remove(k);
                break;
            }
        }
        database.refresh(this);
    }

    // send or recieve a request
    public void sendFriendRequest(User receiver) {
        System.out.println("in send friend requese");
        FriendRequest request = new FriendRequest(this, receiver);
        receiver.receiveFriendRequest(request);
        
    }

    public void receiveFriendRequest(FriendRequest request) {
        friendRequests.add(request);  
        database.refresh(request.getReceiver());
    }

    // accept or decline a request
    public void acceptFriendRequest(FriendRequest request) {
        System.out.println("here in accept in user");
        if (request.getReceiver().getUserId().equals(this.getUserId()) && request.isPending()) {
            System.out.println("here in condition");
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
    public void setFriends(List<User> friends)
    {
        this.friends = friends;
    }
    public List<FriendRequest> getFriendRequests() {
        return friendRequests;
    }
    public void setFriendRequests(List<FriendRequest> requests)
    {
        this.friendRequests = requests;
    }
    public List<User> getBlockedUsers() {
        return blockedUsers;
    }
    public void setBlocked(List<User> blocked)
    {
        this.blockedUsers = blocked;
    }

    public List<User> friendsOfFriends() {
        List<User> fof = new ArrayList<>();
        for (User k : this.getFriends()) {
            for (User u : k.getFriends()) {
                if ((!this.getFriends().contains(u)) && (!this.isBlocked(u)) && !(u.getUserId().equals(this.getUserId()))) {
                    fof.add(u);
                }
            }
        }
        return fof;
    }
    public void update()
    {
        AccountManagement.getAdmin().updateUser(this);
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
