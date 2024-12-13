package connecthub;

import java.util.*;
import java.util.regex.*;

public class AccountManagement {

    private List<User> Users;
    private static AccountManagement admin;

    private AccountManagement() {
        this.Users = new ArrayList<>();
        loadUsers();
    }
    public static AccountManagement getAdmin()
    {
        if(admin==null)
        {
            admin=new AccountManagement();
        }
        return admin;
    }

    // sign up a new user and save in the file
    public User signup(String email, String username, String password, String dateOfBirth, String profilePhoto, String coverPhoto, String bio) {
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
    public List<User> getUsers()
    {
        return Users;
    }

    // logout a user and set as Offline
    public void logout(User user) {
        user.setStatus(false);
        saveUsers();
    }

    // Helping methods
    public boolean emailExists(String email) {
        for (User user : Users) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
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
    public void updateUser(User u)
    {
        loadUsers();
        for(User k : Users)
        {
            if(k.getUserId().equals(u.getUserId()))
            {
                u.setUsername(k.getUsername());
                u.status = k.isStatus();
                u.setRealPassword(k.getPassword());
                u.setBlocked(k.getBlockedUsers());
                u.setFriends(k.getFriends());
                u.setFriendRequests(k.getFriendRequests());
                for(FriendRequest f :k.getFriendRequests())
                {
                    System.out.println(f.getSender().getUsername()+" "+f.getReceiver().getUsername());
                }
                u.changeBio(k.getBio());
                u.changeCoverPhoto(k.getCoverPhoto());
                u.changeProfilePhoto(k.getProfilePhoto());
            }
        }
    }

}
