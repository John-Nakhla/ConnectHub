
package connecthub;

import java.util.*;


public class GroupRequests extends Group{
    private List<GroupJoinRequests> joinRequests;
    
    public GroupRequests(Group group) { 
        super(group.getGroupId(), group.getGroupName(), group.getDescription(), group.getGroupPhoto(), group.getCreatorUsername()); 
        this.joinRequests = new ArrayList<>(); 
    }
 
    public void sendJoinRequest(String senderId,String username) {
        GroupJoinRequests newRequest = new GroupJoinRequests(senderId, username,this.getGroupId());

        // Add the request to the list
        joinRequests.add(newRequest);

    }
    
    // Method to accept a join request
    public void acceptJoinRequest(GroupJoinRequests request) {
        GroupMember member=new GroupMember(request.getSenderId(),request.getUsername(),this.getGroupId());
        members.add(member);
        joinRequests.remove(request);
        request.setStatus("Accepted");

    }
    
    public void rejectJoinRequest(GroupJoinRequests request) {
        joinRequests.remove(request);
        request.setStatus("Rejected");
    }
    
}
