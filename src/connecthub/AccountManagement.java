
package connecthub;

import java.util.*;
import java.util.regex.*;


public class AccountManagement {
    private List<User> Users;

    public AccountManagement() {
        this.Users = new ArrayList<>();
        loadUsers();
    }
    
    public boolean signup(String email, String username, String password, String dateOfBirth, String profilePhoto, String coverPhoto, String bio) {
        if (!isValidEmail(email) || emailExists(email)) {
            return false;
        }

        if (username.isEmpty() || password.isEmpty() || dateOfBirth.isEmpty()) {
            return false;
        }

        User newUser = new User(email, username, password, dateOfBirth, profilePhoto, coverPhoto, bio);
        Users.add(newUser);
        saveUsers();
        return true;
    }
    
    public User login(String email, String password) {
        for (User user : Users) {
            if (user.getEmail().equals(email) && user.checkPassword(password)) {
                user.setStatus(true);
                saveUsers();
                return user;
            }
        }
        return null;
    }


    public void logout(User user) {
        user.setStatus(false);
        saveUsers(); 
    }
    
    private boolean emailExists(String email) {
        
        for (User user : Users) {
            if (user.getEmail().equals(email)) {
                return true; 
            }
        }
        return false; 
    }

    private boolean isValidEmail(String email) {
    Pattern pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    Matcher matcher = pattern.matcher(email);
    return matcher.matches();
    }

    private void saveUsers() {
        UsersDatabase database = new UsersDatabase();
        database.saveUsers(Users);
    }

    private void loadUsers() {
        UsersDatabase database = new UsersDatabase();
        this.Users = database.loadUsers();
    }
    
}
