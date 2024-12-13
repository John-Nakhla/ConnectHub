
package connecthub;

import java.security.MessageDigest;


public class UserManagement {
    
    protected String userId;
    protected String email;
    protected String username;
    protected String password;
    protected String DOB;
    protected boolean status;
    protected String profilePhoto;
    protected String coverPhoto;
    protected String bio;
    private static int users_count = 10000;
    protected UsersDatabase database = new UsersDatabase();
    
    public UserManagement(String email, String username, String password, String DOB, String profilePhoto, String coverPhoto, String bio) {
        this.userId = UniqueId();
        this.email = email;
        this.username = username;
        this.password = generatePassword(password);
        this.DOB = DOB;
        this.profilePhoto = profilePhoto;
        this.coverPhoto = coverPhoto;
        this.bio = bio;
        this.status = false;

    }
    
    private String UniqueId() {
        users_count++;
        return "User" + users_count;
    }    

    public void setRealPassword(String password) {
        this.password = password;
    }
    
    public boolean isStatus() {
        return status;
    }

    // generates hashed password to save in the file
    public String generatePassword(String password) {
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
    
}
