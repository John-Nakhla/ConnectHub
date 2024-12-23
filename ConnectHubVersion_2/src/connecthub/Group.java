
package connecthub;

import java.util.*;
import org.json.*;

public class Group extends GroupManagement{
    
    public List<GroupMember> members;
    public List<GroupAdmin> admins;
    private List<GroupMember> removedMembers;
    private List<GroupJoinRequests> requests;

    public Group(String groupId, String groupName, String description, String groupPhoto, String creatorUsername) {
        super(groupId, groupName, description, groupPhoto, creatorUsername);
        this.members = new ArrayList<>();
        this.admins = new ArrayList<>();
        this.removedMembers = new ArrayList<>();
        this.requests=new ArrayList<>();
    }

    
    // Getters & Setters
    public List<GroupMember> getMembers() {
        return members;
    }

    public List<GroupAdmin> getAdmins() {
        return admins;
    }

    public void setMembers(List<GroupMember> members) {
        this.members = members;
    }
    
    public void setRemovedMembers(List<GroupMember> removedMembers) {
        this.removedMembers = removedMembers;
    }

    // Check if member
    public boolean isMember(String username){
        for(GroupMember member: members){
            if (member.getUsername().equals(username))
                return true;
        }
        return false;
    }
    
    // Check if admin
    public boolean isAdmin(String username){
        for(GroupAdmin admin: admins){
            if (admin.getUsername().equals(username))
                return true;
        }
        return false;
    }
    
    // Check if admin
    public boolean isCreator(String username){
        return super.getCreatorUsername().equals(username);
    }
    
    // Check if removed member
    public boolean isRemovedMember(String username){
        for(GroupMember member: removedMembers){
            if (member.getUsername().equals(username))
                return true;
        }
        return false;
    }
    
    // Check if requested already
    public boolean isRequested(String username){
        for(GroupJoinRequests request: requests){
            if(request.getUsername().equals(username))
                return true;
        }
        return false;
    }
    
    // Add a member
    public void addMember(GroupMember member) {
        members.add(member);
    }
    public void addAdmin(GroupAdmin admin){
        admins.add(admin);
    }

    // Remove a member
    public void removeMember(String username) {
        for (GroupMember member:members){
            if(member.getUsername().equals(username)){
                removedMembers.add(member);
                members.remove(member);
                break;
            }
        }

    }
    public void removeAdmin(String username){
            for(GroupAdmin admin: admins){
            if(admin.getUsername().equals(username)){
                removedMembers.add(admin);
                admins.remove(admin);
                break;
            }  
        }  
    }
    
    // Send join request
    public void sendJoinRequest(String username,String userId){
        
        GroupJoinRequests joinRequest = new GroupJoinRequests(userId,username,super.getGroupId());
        requests.add(joinRequest);
        saveToFile();
    }
    
    // Remove join request
    public void removeJoinRequest(GroupJoinRequests req){
        requests.remove(req);
    }
    
    // Accept join request
    public void acceptJoinRequest(String username,GroupJoinRequests req){
        requests.remove(req);
        addMember(new GroupMember(username, super.getGroupName()));
    }
    
    // Get / Set join requests
    public List<GroupJoinRequests> getJoinRequests(){
        return requests;
    }

    public void setJoinRequests(List<GroupJoinRequests> requests) {
        this.requests = requests;
    }

    
    // Delete group from database
    public void deleteFromDatabase(){
        GroupsDatabase db = new GroupsDatabase();
        db.deleteGroup(super.getGroupId());
    }
    
    // Get all Group People
    public List<String> GetGroupPeople(){
        List<String> groupPeople = new ArrayList<>();
        groupPeople.add(super.getCreatorUsername() + " (Creator)");
        for(GroupAdmin admin: admins){
            groupPeople.add(admin.getUsername() + " (Admin)");
        }
        for(GroupMember member: members){
            groupPeople.add(member.getUsername() + " (Member)");
        }
        return groupPeople;
    }
    
    // Save this content to file
    public void saveToFile() {
        GroupsDatabase db = new GroupsDatabase();
        db.deleteGroup(super.getGroupId());
        JSONArray groupsArray = db.loadGroups();

        JSONObject groupObj = new JSONObject();
        groupObj.put("groupId", super.getGroupId());
        groupObj.put("groupName", super.getGroupName());
        groupObj.put("description", super.getDescription());
        groupObj.put("groupPhoto", super.getGroupPhoto());
        groupObj.put("creatorUsername", super.getCreatorUsername());
        
        JSONArray groupMembers = new JSONArray();
        for (GroupMember member : members) {
            JSONObject memberObj = new JSONObject();
            memberObj.put("userName", member.getUsername());
            memberObj.put("groupName", member.getGroupname());
            groupMembers.put(memberObj);
        }
        groupObj.put("members", groupMembers);
        
        JSONArray groupAdmins = new JSONArray();
        for (GroupAdmin admin : admins) {
            JSONObject adminObj = new JSONObject();
            adminObj.put("userName", admin.getUsername());
            adminObj.put("groupName", admin.getGroupname());
            groupAdmins.put(adminObj);
        }
        groupObj.put("admins", groupAdmins);
        
        JSONArray groupremovedMembers = new JSONArray();
        for (GroupMember member : removedMembers) {
            JSONObject memberObj = new JSONObject();
            memberObj.put("userName", member.getUsername());
            memberObj.put("groupName", member.getGroupname());
            groupremovedMembers.put(memberObj);
        }
        groupObj.put("removedMembers", groupremovedMembers);
        
        JSONArray joinRequests = new JSONArray();
        for (GroupJoinRequests request : requests) {
            JSONObject requestObj = new JSONObject();
            requestObj.put("username", request.getUsername());
            requestObj.put("userId", request.getSenderId());
            requestObj.put("groupId", request.getGroupId());
            joinRequests.put(requestObj);
        }
        groupObj.put("joinRequests", joinRequests);

        groupsArray.put(groupObj);
        db.saveGroups(groupsArray);
    } 

    public List<GroupJoinRequests> getRequests() {
        return requests;
    }
    
    public void addJoinRequest(GroupJoinRequests r)
    {
        this.requests.add(r);
        
    }
}


