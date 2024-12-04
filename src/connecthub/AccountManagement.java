/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connecthub;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class AccountManagement {
    private List<User> Users;

    public AccountManagement() {
        this.Users = new ArrayList<>();
        loadUsers();
    }
    
    public boolean signup(String email, String username, String password, String dateOfBirth) {
        if (!isValidEmail(email) || emailExists(email)) {
             JOptionPane.showMessageDialog(null, "Invalid email. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        if (username.isEmpty() || password.isEmpty() || dateOfBirth.isEmpty()) {
           JOptionPane.showMessageDialog(null, "Some fields are empty.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        User newUser = new User(email, username, password, dateOfBirth);
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
