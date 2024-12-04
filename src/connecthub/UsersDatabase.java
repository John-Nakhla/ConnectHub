/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connecthub;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONObject;


/**
 *
 * @author ADMIN
 */
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
                        userJson.getString("dateOfBirth")
                );
                user.setStatus(userJson.getBoolean("isOnline"));

                JSONArray friendsArray = userJson.optJSONArray("friends");
                if (friendsArray != null) {
                    for (int j = 0; j < friendsArray.length(); j++) {
                        user.addFriend(friendsArray.getString(j));
                    }
                }
                users.add(user);
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
            userArray.put(userJson);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write(userArray.toString(4));
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }
}

