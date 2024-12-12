
package connecthub;

import java.util.*;
import org.json.*;

public class Group extends GroupManagement{
    
    public List<GroupMember> members;
    public List<GroupAdmin> admins;
    private List<GroupMember> removedMembers;
    private List<Posts> posts;
    private List<String> requests;

    public Group(String groupId, String groupName, String description, String groupPhoto, String creatorUsername) {
        super(groupId, groupName, description, groupPhoto, creatorUsername);
        this.members = new ArrayList<>();
        this.admins = new ArrayList<>();
        this.removedMembers = new ArrayList<>();
        this.posts = new ArrayList<>();
        this.requests = new ArrayList<>();
    }

    
    // Getters & Setters
    public List<GroupMember> getMembers() {
        return members;
    }

    public List<GroupAdmin> getAdmins() {
        return admins;
    }

    public List<Posts> getPosts() {
        return posts;
    }
    
    public String getGroupId() {
        return super.getGroupId();
    }
    

    public void setMembers(List<GroupMember> members) {
        this.members = members;
    }
    
    public void setRemovedMembers(List<GroupMember> removedMembers) {
        this.removedMembers = removedMembers;
    }

    public void setAdmins(List<GroupAdmin> admins) {
        this.admins = admins;
    }

    public void setPosts(List<Posts> posts) {
        this.posts = posts;
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
        for(String request: requests){
            if(request.equals(username))
                return true;
        }
        return false;
    }
    
    // Add a member
    public void addMember(GroupMember member) {
        members.add(member);
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
        for(GroupAdmin admin: admins){
            if(admin.getUsername().equals(username)){
                removedMembers.add(admin);
                members.remove(admin);
                break;
            }  
        }
    }
    
    // Send join request
    public void sendJoinRequest(String username){
        requests.add(username);
    }
    
    // Remove join request
    public void removeJoinRequest(String username){
        requests.remove(username);
    }
    
    // Accept join request
    public void acceptJoinRequest(String username){
        requests.remove(username);
        addMember(new GroupMember(username, super.getGroupName()));
    }
    
    // Get / Set join requests
    public List<String> getJoinRequests(){
        return requests;
    }

    public void setJoinRequests(List<String> requests) {
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
        for (GroupAdmin member : admins) {
            JSONObject memberObj = new JSONObject();
            memberObj.put("userName", member.getUsername());
            memberObj.put("groupName", member.getGroupname());
            groupMembers.put(memberObj);
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
        for (String request : requests) {
            JSONObject requestObj = new JSONObject();
            requestObj.put("userName", request);
            joinRequests.put(requestObj);
        }
        groupObj.put("joinRequests", joinRequests);

        JSONArray groupPosts = new JSONArray();
        for (Posts post : posts) {
            JSONObject postsObj = new JSONObject();
            postsObj.put("postId", post.getPostId());
            postsObj.put("userName", post.getUsername());
            postsObj.put("groupName", post.getGroupname());
            postsObj.put("content", post.getContent());
            postsObj.put("img", post.getImg());
            postsObj.put("timestamp", post.getTimestamp().toString());
            groupPosts.put(postsObj);
        }
        groupObj.put("posts", groupPosts);

        groupsArray.put(groupObj);
        db.saveGroups(groupsArray);
    } 
}

