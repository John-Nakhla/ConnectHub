/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connecthub;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class NewsFeed {
    private UsersDatabase usersDatabase;
    private List<Posts> allPosts;

    public NewsFeed(UsersDatabase usersDatabase) {
        this.usersDatabase = usersDatabase;
        this.allPosts = new ArrayList<>();
        
    }
    
    
    public List<Posts> PostsAndStories(String userId) {
        List<Posts> friendPosts = new ArrayList<>();
        User currentUser = getUserById(userId);

        if (currentUser != null) {
            for (String friendId : currentUser.getFriends()) {
                for (Posts post : allPosts) {
                    if (post.getUserId().equals(friendId)) {
                        friendPosts.add(post);
                    }
                }
            }
        }
        return friendPosts;
    }

     public List<String> listFriendsWithStatus(String userId) {
        List<String> friendsStatus = new ArrayList<>();
        User currentUser = getUserById(userId);

        if (currentUser != null) {
            for (String friendId : currentUser.getFriends()) {
                User friend = getUserById(friendId);
                if (friend != null) {
                    String status = friend.isStatus() ? "Online" : "Offline";
                    friendsStatus.add(friend.getUsername() + " (" + status + ")");
                }
            }
        }
        return friendsStatus;
    }

    public List<String> getFriendSuggestions(String userId) {
        List<String> suggestions = new ArrayList<>();
        User currentUser = getUserById(userId);

        if (currentUser != null) {
            for (User user : usersDatabase.loadUsers()) {
                if (!currentUser.getFriends().contains(user.getUserId()) && !user.getUserId().equals(userId)) {
                    suggestions.add(user.getUsername());
                }
            }
        }
        return suggestions;
    }

    
    public void addPost(String userId, String content, String type) {
        allPosts.add(new Posts(userId, content, type, new Date().toString()));
    }

    private User getUserById(String userId) {
        for (User user : usersDatabase.loadUsers()) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null;
    }
    
    
    
}

