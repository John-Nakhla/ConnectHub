
package connecthub;

import org.json.*;
import java.io.*;
import java.time.LocalDateTime;

public class Content {
    private final String contentId;
    private final String authorId;
    private final String content;
    private final String img;
    private final LocalDateTime timestamp;
    private final boolean isStory;
    private final String filename = "content.json";

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
    
    
    
    public void createContent(String authorId, String content, String img, boolean isStory) {
        //validation can be removed and added as an errror msg in frontend
        if ((content == null || content.trim().isEmpty()) && (img == null || img.trim().isEmpty())) {
            System.out.println("Content cannot be empty.");
            return;
        }
        //
        
        String contentId = String.valueOf(uniqueId());
        Content newContent = new Content(contentId, authorId, content, img, isStory);
        newContent.saveToFile();
    }
    
    private int uniqueId() {
        int nextId = 20000;
        JSONArray contentArray = loadFromFile();

        if (contentArray.length() > 0) {
            JSONObject lastContent = contentArray.getJSONObject(0);
            nextId = lastContent.getInt("contentId") + 1;
        }
        return nextId;
    }
    
    public JSONArray loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            StringBuilder contentBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line);
            }
            return new JSONArray(contentBuilder.toString());
        } catch (IOException e) {
            return new JSONArray();
        }
    }
    
    
    public void saveToFile() {
        JSONArray contentArray = loadFromFile();
        JSONObject contentObj = new JSONObject();
        contentObj.put("contentId", this.contentId);
        contentObj.put("authorId", this.authorId);
        contentObj.put("content", this.content);
        contentObj.put("img", this.img);
        contentObj.put("timestamp", this.timestamp.toString());
        contentObj.put("isStory", this.isStory);
                
        JSONArray updatedArray = new JSONArray();
        updatedArray.put(contentObj);

        for (int i = 0; i < contentArray.length(); i++) {
            updatedArray.put(contentArray.get(i));
        }
        
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(updatedArray.toString(4));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteExpiredStories() {
        JSONArray contentArray = loadFromFile();
        JSONArray updatedArray = new JSONArray();
        
        LocalDateTime now = LocalDateTime.now();
        for (int i = 0; i < contentArray.length(); i++) {
            JSONObject contentObj = contentArray.getJSONObject(i);
            boolean isStory = contentObj.getBoolean("isStory");
            LocalDateTime timestamp = LocalDateTime.parse(contentObj.getString("timestamp"));
            
            if (!isStory || java.time.Duration.between(timestamp, now).toHours() < 24) {
                updatedArray.put(contentObj);
            }
        }
        
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(updatedArray.toString(4));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
