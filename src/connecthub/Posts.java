
package connecthub;

import java.time.LocalDateTime;
import org.json.*;


public class Posts {
    
    private final String postId;
    private final String authorId;
    private final String groupId;
    private String content;
    private String img;
    private LocalDateTime timestamp;

    public Posts(String postId, String authorId, String groupId, String content, String img) {
        this.postId = postId;
        this.authorId = authorId;
        this.groupId = groupId;
        this.content = content;
        this.img = img;
        this.timestamp = LocalDateTime.now();
    }

    // Getters & Setters
    public String getPostId() {
        return postId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public String getGroupId() {
        return groupId;
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
        postsObj.put("authorId", this.authorId);
        postsObj.put("groupId", this.groupId);
        postsObj.put("content", this.content);
        postsObj.put("img", this.img);
        postsObj.put("timestamp", this.timestamp.toString());

        postsArray.put(postsObj);
        db.savePosts(postsArray);
        db.savePosts(db.sortPostsByTimestamp(db.loadPosts()));
                
    } 
}
