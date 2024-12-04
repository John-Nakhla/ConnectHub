
package connecthub;

import org.json.*;
import java.io.*;
import java.time.*;

public class Content {
    private final String contentId;
    private final String authorId;
    private final String content;
    private final LocalDateTime timestamp;
    private final boolean isStory;
    private static final String filename = "content.json";

    public Content(String contentId, String authorId, String content, boolean isStory) {
        this.contentId = contentId;
        this.authorId = authorId;
        this.content = content;
        this.timestamp = LocalDateTime.now();
        this.isStory = isStory;
    }
    
    public static void createContent(String authorId, String content, boolean isStory) {
        //validation can be removed and added as an errror msg in frontend
        if (content == null || content.trim().isEmpty()) {
            System.out.println("Content cannot be empty.");
            return;
        }
        //
        
        String contentId = String.valueOf(uniqueId());
        Content newContent = new Content(contentId, authorId, content, isStory);
        newContent.saveToFile();
    }
    
    private static int uniqueId() {
        int nextId = 20000;
        JSONArray contentArray = loadFromFile();

        if (contentArray.length() > 0) {
            JSONObject lastContent = contentArray.getJSONObject(contentArray.length() - 1);
            nextId = lastContent.getInt("contentId") + 1;
        }
        return nextId;
    }
    
    public static JSONArray loadFromFile() {
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
        contentObj.put("timestamp", this.timestamp.toString());
        contentObj.put("isStory", this.isStory);
        
        contentArray.put(contentObj);
        
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(contentArray.toString(4));
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
