package connecthub;

import java.util.*;
import java.util.regex.*;

public class AccountManagement {

    private List<User> Users;

    public AccountManagement() {
        this.Users = new ArrayList<>();
        loadUsers();
    }

    // sign up a new user and save in the file
    public User signup(String email, String username, String password, String dateOfBirth, String profilePhoto, String coverPhoto, String bio) {
        if (!isValidEmail(email) || emailExists(email)) {
            return null;
        }

        if (username.isEmpty() || password.isEmpty() || dateOfBirth.isEmpty()) {
            return null;
        }

        User newUser = new User(email, username, password, dateOfBirth, profilePhoto, coverPhoto, bio);
        Users.add(newUser);
        saveUsers();
        return newUser;
    }

    // login a user and set as Online
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

    // logout a user and set as Offline
    public void logout(User user) {
        user.setStatus(false);
        saveUsers();
    }

    // Helping methods
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
