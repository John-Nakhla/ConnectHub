
package backend;

import org.json.*;
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;


public class ContentDatabase {
    
    private final String FILE_PATH = "content.json";
    

    // Load content from file
    public JSONArray loadContent() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            StringBuilder contentBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line);
            }
            return new JSONArray(contentBuilder.toString());
        } catch (IOException e) {
            System.out.println("Error loading content: " + e.getMessage());
            return new JSONArray();
        }
    }

    // Save content to file
    public void saveContent(JSONArray contentArray) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            writer.write(contentArray.toString(4));  // Pretty-print JSON
        } catch (IOException e) {
            System.out.println("Error saving content: " + e.getMessage());
        }
    }
    
    // Create content with unique id
    public void createContent(String authorId, String content, String img, boolean isStory) {

        String contentId = String.valueOf(uniqueId());
        Content newContent = new Content(contentId, authorId, content, img, isStory);
        newContent.saveToFile();
    }
    
    private int uniqueId() {
        JSONArray contentArray = loadContent();
        int nextId = 20000;

        if (contentArray.length() > 0) {
            JSONObject lastContent = contentArray.getJSONObject(0);
            nextId = lastContent.getInt("contentId") + 1;
        }
        return nextId;
    }


    // Delete expired stories
    public void deleteExpiredStories() {
        JSONArray contentArray = loadContent();
        JSONArray updatedArray = new JSONArray();
        
        LocalDateTime now = LocalDateTime.now();
        for (int i = 0; i < contentArray.length(); i++) {
            JSONObject contentObj = contentArray.getJSONObject(i);
            boolean isStory = contentObj.getBoolean("isStory");
            LocalDateTime timestamp = LocalDateTime.parse(contentObj.getString("timestamp"));
            
            // Keep stories less than 24 hours old and all posts
            if (!isStory || java.time.Duration.between(timestamp, now).toHours() < 24) {
                updatedArray.put(contentObj);
            }
        }
        saveContent(updatedArray);
    }

    // Sort content by timestamp: newest to oldest
    public JSONArray sortContentByTimestamp(JSONArray contentArray) {
        List<JSONObject> contentList = new ArrayList<>();
        
        for (int i = 0; i < contentArray.length(); i++) {
            contentList.add(contentArray.getJSONObject(i));
        }

        contentList.sort((a, b) -> {
            LocalDateTime timeA = LocalDateTime.parse(a.getString("timestamp"));
            LocalDateTime timeB = LocalDateTime.parse(b.getString("timestamp"));
            return timeB.compareTo(timeA);  // Descending order
        });

        return new JSONArray(contentList);
    }
}
