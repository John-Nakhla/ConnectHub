package connecthub;

import java.util.*;
import org.json.*;

public class NewsFeed {

    private UsersDatabase usersDatabase;
    private List<Content> allPosts;
    ContentDatabase db = new ContentDatabase();

    public NewsFeed(UsersDatabase usersDatabase) {
        this.usersDatabase = usersDatabase;
        this.allPosts = convertJsonArrayToContentList(db.loadContent());
    }

    // friend posts and stories
    public List<Content> PostsAndStories(User user) {
        allPosts = convertJsonArrayToContentList(db.loadContent());
        List<Content> ALlPosts = new ArrayList<>();
        if (user != null) {
            for (Content post : allPosts) {
                for (User friend : user.getFriends()) {
                    if (post.getAuthorId().equals(friend.getUserId())) {
                        System.out.println("friend post added\n");
                        ALlPosts.add(post);
                        break;
                    }
                }
                if (post.getAuthorId().equals(user.getUserId())) {
                    System.out.println("user post added\n");
                    ALlPosts.add(post);
                }
            }
        }
        return ALlPosts;
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
    public List<Content> getPostsOnly(User user) {
        List<Content> posts = new ArrayList<>();
        List<Content> postsAndStories = PostsAndStories(user);
        for (Content content : postsAndStories) {
            if (!content.isStory()) {
                posts.add(content);
            }
        }
        return posts;
    }

    // returns all stories only 
    public List<Content> getStoriesOnly(User user) {
        List<Content> stories = new ArrayList<>();
        List<Content> friendsStories = PostsAndStories(user);

        for (Content content : friendsStories) {
            if (content.isStory()) {
                stories.add(content);
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

    //Helping method to convert JsonArray to Content List
    public List<Content> convertJsonArrayToContentList(JSONArray jsonArray) {
        List<Content> contentList = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject contentJson = jsonArray.getJSONObject(i);

            if (!contentJson.has("contentId") || !contentJson.has("authorId") || !contentJson.has("content") || !contentJson.has("timestamp")) {
                continue;
            }

            Content content = new Content(
                    contentJson.getString("contentId"),
                    contentJson.getString("authorId"),
                    contentJson.getString("content"),
                    contentJson.optString("img", ""),
                    contentJson.getBoolean("isStory")
            );

            content.setTimestamp(contentJson.getString("timestamp"));
            contentList.add(content);
        }
        return contentList;
    }
}
