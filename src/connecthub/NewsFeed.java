
package backend;

import java.util.*;


public class NewsFeed {
    private final UsersDatabase usersDatabase;
    private final List<Content> allPosts;

    public NewsFeed(UsersDatabase usersDatabase) {
        this.usersDatabase = usersDatabase;
        this.allPosts = new ArrayList<>();
        
    }
        
    // friend posts and stories
    public List<Content> PostsAndStories(User user) {
        List<Content> friendPosts = new ArrayList<>();

        if (user != null) {
            for (User friend : user.getFriends()) {
                for (Content post : allPosts) {
                    if (post.getAuthorId().equals(friend.getUserId())) {
                        friendPosts.add(post);
                    }
                }
            }
        }
        return friendPosts;
    }

    // friends' status 
    public List<String> listFriendsWithStatus(User user) {
        List<String> friendsStatus = new ArrayList<>();

        if (user != null) {
            for (User friend : user.getFriends()) {
                if (friend != null) {
                    String status = friend.isStatus() ? "Online" : "Offline";
                    friendsStatus.add(friend.getUsername() + " (" + status + ")");
                }
            }
        }
        return friendsStatus;
    }

    // friend suggestions
    public List<String> getFriendSuggestions(String userId) {
        List<String> suggestions = new ArrayList<>();
        User currentUser = getUserById(userId);

        if (currentUser != null) {
            for (User user : usersDatabase.loadUsers()) {
                if (!currentUser.getFriends().contains(user) && !user.getUserId().equals(userId)) {
                    suggestions.add(user.getUsername());
                }
            }
        }
        return suggestions;
    }

    // returns user with a given id
    public User getUserById(String userId) {
        for (User user : usersDatabase.loadUsers()) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null;
    }
    
    // returns all posts only 
    public List<Content> getPostsOnly() {
        List<Content> posts = new ArrayList<>();
        for (Content post : allPosts) {
            if (!post.isStory()) {
                posts.add(post);
            }
        }
        return posts;
    }

    // returns all stories only 
    public List<Content> getStoriesOnly() {
        List<Content> stories = new ArrayList<>();
        for (Content post : allPosts) {
            if (post.isStory()) {
                stories.add(post);
            }
        }
        return stories;
    }
    
    // returns a content of a specific user 
    public List<Content> getContentById(String id) {
        List<Content> content = new ArrayList<>();
        for (Content post : allPosts) {
            if (post.getAuthorId().equals(id)) {
                content.add(post);
            }
        }
        return content;
    }
}

