package connecthub;

import java.io.*;
import java.util.*;
import org.json.*;

public class UsersDatabase {
    
    private static final String FILE_PATH = "users.json";

    // Load users from file 
    public void refresh(User u) {
        List<User> users = this.loadUsers();
        for (User k : users) {
            if (k.getUserId().equals(u.getUserId())) {
                users.remove(k);
                users.add(u);
                this.saveUsers(users);
            }
        }
    }
    
    public List<User> loadUsers() {
        ArrayList<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            StringBuilder Data = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                Data.append(line);
            }
            JSONArray userArray = new JSONArray(Data.toString());
            for (int i = 0; i < userArray.length(); i++) {
                JSONObject userJson = userArray.getJSONObject(i);
                
                if (!userJson.has("email") || !userJson.has("username") || !userJson.has("password") || !userJson.has("dateOfBirth")) {
                    continue;
                }
                
                User user = new User(
                        userJson.getString("email"),
                        userJson.getString("username"),
                        userJson.getString("password"),
                        userJson.getString("dateOfBirth"),
                        userJson.optString("profilePhoto", ""),
                        userJson.optString("coverPhoto", ""),
                        userJson.optString("bio", "")
                );
                user.setRealPassword(userJson.getString("password"));
                user.status = userJson.getBoolean("isOnline");
                user.setID(userJson.getString("userId"));
                users.add(user);
            }
            for (int i = 0; i < userArray.length(); i++) {
                JSONObject userJson = userArray.getJSONObject(i);
                User user = users.get(i);
                
                JSONArray friendsArray = userJson.optJSONArray("friends");
                
                if (friendsArray != null && friendsArray.length() > 0) {
                    for (int j = 0; j < friendsArray.length(); j++) {
                        String friendId = friendsArray.getString(j);
                        for (User Friend : users) {
                            if (Friend.getUserId().equals(friendId)) {
                                user.addFriend(Friend);
                                break;
                            }
                        }
                    }
                }
                
                JSONArray friendRequestsSendersArray = userJson.optJSONArray("FriendRequestSenders");
                
                if (friendRequestsSendersArray != null && friendRequestsSendersArray.length() > 0) {
                    for (int j = 0; j < friendRequestsSendersArray.length(); j++) {
                        String SenderId = friendRequestsSendersArray.getString(j);
                        for (User sender : users) {
                            if (sender.getUserId().equals(SenderId)) {
                                user.receiveFriendRequest(new FriendRequest(sender, user));
                                break;
                            }
                        }
                    }
                }
                
                JSONArray blockedFriendsArray = userJson.optJSONArray("blockedFriends");
                
                if (blockedFriendsArray != null && blockedFriendsArray.length() > 0) {
                    for (int j = 0; j < blockedFriendsArray.length(); j++) {
                        String blockedId = blockedFriendsArray.getString(j);
                        for (User blocked : users) {
                            if (blocked.getUserId().equals(blockedId)) {
                                user.blockUser(blocked);
                                break;
                            }
                        }
                    }
                }
            }
            
        } catch (IOException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
        return users;
    }

    // save users to file 
    public void saveUsers(List<User> users) {
        JSONArray userArray = new JSONArray();
        for (User user : users) {
            JSONObject userJson = new JSONObject();
            userJson.put("userId", user.getUserId());
            userJson.put("email", user.getEmail());
            userJson.put("username", user.getUsername());
            userJson.put("password", user.getPassword());
            userJson.put("dateOfBirth", user.getDOB());
            userJson.put("isOnline", user.isStatus());
            JSONArray friendsArray = new JSONArray();
            for (User friend : user.getFriends()) {
                friendsArray.put(friend.getUserId());
            }
            userJson.put("friends", friendsArray);
            JSONArray friendRequestsArray = new JSONArray();
            for (FriendRequest request : user.getFriendRequests()) {
                friendRequestsArray.put(request.getSender().getUserId());
            }
            userJson.put("FriendRequestSenders", friendRequestsArray);
            JSONArray blockedFriendsArray = new JSONArray();
            for (User blocked : user.getBlockedUsers()) {
                blockedFriendsArray.put(blocked.getUserId());
            }
            userJson.put("blockedFriends", blockedFriendsArray);
            userJson.put("profilePhoto", user.getProfilePhoto());
            userJson.put("coverPhoto", user.getCoverPhoto());
            userJson.put("bio", user.getBio());
            userArray.put(userJson);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write(userArray.toString(4));
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }
}
