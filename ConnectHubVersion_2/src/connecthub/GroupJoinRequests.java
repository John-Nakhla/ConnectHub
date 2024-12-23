/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connecthub;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class GroupJoinRequests{
    private String senderId;      // The user who sent the join request
    private String username;
    private String groupId;       // The group to join
    private String status;

    public GroupJoinRequests(String senderId,String username, String groupId) {
        this.senderId = senderId;
        this.groupId = groupId;
        this.username= username;
        this.status = "Pending";
    }
    //Getters
    public String getSenderId() {
        return senderId;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getStatus() {
        return status;
    }

    public String getUsername() {
        return username;
    }
    

    //Setters
    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
        
   
    
}
