
package connecthub;

import java.time.LocalDateTime;
import org.json.*;


public class Post {
    
    private final String postId;
    private final String username;
    private final String groupname;
    private String content;
    private String img;
    private LocalDateTime timestamp;

    public Post(String postId, String username, String groupname, String content, String img) {
        this.postId = postId;
        this.username = username;
        this.groupname = groupname;
        this.content = content;
        this.img = img;
        this.timestamp = LocalDateTime.now();
    }

    // Getters & Setters
    public String getPostId() {
        return postId;
    }

    public String getUsername() {
        return username;
    }

    public String getGroupname() {
        return groupname;
    }

    public String getContent() {
        return content;
    }

    public String getImg() {
        return img;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = LocalDateTime.parse(timestamp);
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setImg(String img) {
        this.img = img;
    }

    
    // Save this post to file
    public void saveToFile() {
        PostsDatabase db = new PostsDatabase();
        JSONArray postsArray = db.loadPosts();

        JSONObject postsObj = new JSONObject();
        postsObj.put("postId", this.postId);
        postsObj.put("username", this.username);
        postsObj.put("groupname", this.groupname);
        postsObj.put("content", this.content);
        postsObj.put("img", this.img);
        postsObj.put("timestamp", this.timestamp.toString());

        postsArray.put(postsObj);
        db.savePosts(postsArray);
        db.savePosts(db.sortPostsByTimestamp(db.loadPosts()));
                
    } 
}
