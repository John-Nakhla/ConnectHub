
package backend;

import org.json.*;
import java.time.LocalDateTime;

public class Content {
    private final String contentId;
    private final String authorId;
    private final String content;
    private final String img;
    private final LocalDateTime timestamp;
    private final boolean isStory;

    public Content(String contentId, String authorId, String content, String img, boolean isStory) {
        this.contentId = contentId;
        this.authorId = authorId;
        this.content = content;
        this.img = img;
        this.timestamp = LocalDateTime.now();
        this.isStory = isStory;
    }

    public String getContentId() {
        return contentId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public String getContent() {
        return content;
    }

    public String getImg() {
        return img;
    }

    public boolean isStory() {
        return isStory;
    }

    // Save this content to file
    public void saveToFile() {
        ContentDatabase db = new ContentDatabase();
        JSONArray contentArray = db.loadContent();

        JSONObject contentObj = new JSONObject();
        contentObj.put("contentId", this.contentId);
        contentObj.put("authorId", this.authorId);
        contentObj.put("content", this.content);
        contentObj.put("img", this.img);
        contentObj.put("timestamp", this.timestamp.toString());
        contentObj.put("isStory", this.isStory);

        contentArray.put(0, contentObj);
        db.saveContent(contentArray);
    }
}

