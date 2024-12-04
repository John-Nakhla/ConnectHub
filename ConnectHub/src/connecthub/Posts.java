/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connecthub;

/**
 *
 * @author ADMIN
 */
public class Posts {
    private String userId;
    private String content;
    private String type;
    private String timestamp;

    public Posts(String userId, String content, String type, String timestamp) {
        this.userId = userId;
        this.content = content;
        this.type = type;
        this.timestamp = timestamp;
    }

    public String getUserId() {
        return userId;
    }

    public String getContent() {
        return content;
    }

    public String getType() {
        return type;
    }

    public String getTimestamp() {
        return timestamp;
    }


}
