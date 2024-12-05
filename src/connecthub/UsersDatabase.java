
package connecthub;

import java.io.*;
import java.util.*;
import org.json.*;


public class UsersDatabase {
    private static final String FILE_PATH = "users.json";

    public List<User> loadUsers() {
        List<User> users = new ArrayList<>();
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
                        userJson.getString("profilePhoto"),
                        userJson.getString("coverPhoto"),
                        userJson.getString("bio")
                );
                user.setStatus(userJson.getBoolean("isOnline"));
                users.add(user);
                
            for (int k = 0; k < userArray.length(); k++) {
                    JSONObject newuserJson = userArray.getJSONObject(i);
                    User newuser = users.get(k);

                    JSONArray friendsArray = userJson.optJSONArray("friends");
                    if (friendsArray != null) {
                        for (int j = 0; j < friendsArray.length(); j++) {
                            String friendId = friendsArray.getString(j);
                            User friend = findUserById(users, friendId);
                            if (friend != null) {
                                user.addFriend(friend);
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
            userJson.put("friends", user.getFriends());
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
    
    private User findUserById(List<User> users, String userId) {
    for (User user : users) {
        if (user.getUserId().equals(userId)) {
            return user;
        }
    }
    return null; 
}
}

