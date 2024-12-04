/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connecthub;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class User {
    private String userId;
    private String email;
    private String username;
    private String password;
    private String DOB;
    private boolean status;
    private List<String> friends;
    private static int users_count=10000;

    public User( String email, String username, String password, String DOB) {
        this.userId = UniqueId();
        this.email = email;
        this.username = username;
        this.password = generatePassword(password);
        this.DOB = DOB;
        this.status = false;
        this.friends = new ArrayList<>();
    }
    
    private String UniqueId(){
        users_count++; 
        return "User" + users_count;
    }
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
    
    public boolean checkPassword(String password) {
        return this.password.equals(generatePassword(password));
    }

    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public boolean isStatus() {
        return status;
    }


    public List<String> getFriends() {
        return new ArrayList<>(friends); 
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    public void addFriend(String friendUsername) {
        if (!friends.contains(friendUsername)) {
            friends.add(friendUsername);
        }
    }
    public void removeFriend(String friendUsername) {
        
            friends.remove(friendUsername);
        
    }

    public String getDOB() {
        return DOB;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
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
