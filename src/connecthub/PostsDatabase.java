
package connecthub;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import org.json.*;


public class PostsDatabase {
    
    private final String FILE_PATH = "groups content.json";
    private List<Posts> allPosts = convertJsonArrayToPostList(loadPosts());
    

    // Load posts from file
    public JSONArray loadPosts() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            StringBuilder contentBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line);
            }
            return new JSONArray(contentBuilder.toString());
        } catch (IOException e) {
            System.out.println("Error loading posts: " + e.getMessage());
            return new JSONArray();
        }
    }

    // Save posts to file
    public void savePosts(JSONArray contentArray) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            writer.write(contentArray.toString(4));
        } catch (IOException e) {
            System.out.println("Error saving posts: " + e.getMessage());
        }
    }
    
    // Create post with unique id
    public void createPost(String authorId, String content, String groupId, String img) {

        String postId = String.valueOf(uniqueId());
        Posts newPost = new Posts(postId, authorId, groupId, content, img);
        newPost.saveToFile();
    }
    
    private int uniqueId() {
        JSONArray postsArray = loadPosts();
        int nextId = 40000;

        if (postsArray.length() > 0) {
            JSONObject lastPost = postsArray.getJSONObject(0);
            nextId = lastPost.getInt("postId") + 1;
        }
        return nextId;
    }
    
    
    // Delete a post by postId
    public boolean deletePost(String postId) {
        JSONArray postsArray = loadPosts();
        boolean postFound = false;

        for (int i = 0; i < postsArray.length(); i++) {
            JSONObject post = postsArray.getJSONObject(i);
            if (post.getString("postId").equals(postId)) {
                postsArray.remove(i);
                postFound = true;
                break;
            }
        }

        if (postFound) {
            savePosts(postsArray);
            return true;
        } else {
            return false;
        }
    }
    
    // Get post by id
    public Posts getPostById(String postId){
        for(Posts post: allPosts){
            if (post.getPostId().equals(postId)){
                return post;
            }
        }
        return null;
    }

    
    // returns member's posts in a group 
    public List<Posts> getMemberPostsById(String memberId, String groupId) {
        List<Posts> content = new ArrayList<>();
        for (Posts post : allPosts) {
            if (post.getAuthorId().equals(memberId) && post.getGroupId().equals(groupId)) {
                content.add(post);
            }
        }
        return content;
    }
        
    // returns posts of a specific group 
    public List<Posts> getGroupPostsById(String groupId) {
        List<Posts> content = new ArrayList<>();
        for (Posts post : allPosts) {
            if (post.getGroupId().equals(groupId)) {
                content.add(post);
            }
        }
        return content;
    }


    // Sort posts by timestamp: newest to oldest
    public JSONArray sortPostsByTimestamp(JSONArray postsArray) {
        List<JSONObject> postsList = new ArrayList<>();
        
        for (int i = 0; i < postsArray.length(); i++) {
            postsList.add(postsArray.getJSONObject(i));
        }

        postsList.sort((a, b) -> {
            LocalDateTime timeA = LocalDateTime.parse(a.getString("timestamp"));
            LocalDateTime timeB = LocalDateTime.parse(b.getString("timestamp"));
            return timeB.compareTo(timeA); 
        });

        return new JSONArray(postsList);
    }
    
    //Helping method to convert JsonArray to Posts List
    public List<Posts> convertJsonArrayToPostList(JSONArray jsonArray) {
        List<Posts> postsList = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject postJson = jsonArray.getJSONObject(i);

            Posts post = new Posts(
                postJson.getString("postId"),
                postJson.getString("authorId"),
                postJson.getString("groupId"),
                postJson.getString("content"),
                postJson.optString("img", "")
            );

            post.setTimestamp(postJson.getString("timestamp"));
            postsList.add(post);
        }

        return postsList;
    }
}


