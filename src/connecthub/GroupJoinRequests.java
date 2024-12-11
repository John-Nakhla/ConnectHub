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
public class GroupJoinRequests extends Group {
    private List<FriendRequest> friendRequests;
    
    public GroupJoinRequests(String groupId, String groupName, String description, String groupPhoto, String creatorId) {
        super(groupId, groupName, description, groupPhoto, creatorId);
        this.friendRequests = new ArrayList<>();
        
    }
    
}
