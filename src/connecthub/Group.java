
package connecthub;

import java.util.*;
import org.json.*;

public class Group {
    private final String groupId;
    private String groupName;
    private String description;
    private String groupPhoto;
    private final String creatorUsername;
    public List<GroupMember> members;
    public List<GroupAdmin> admins;
    private List<GroupMember> removedMembers;
    private List<Post> posts;
    private List<String> groupPeople;
    private List<String> requests;

    public Group(String groupId, String groupName, String description, String groupPhoto, String creatorUsername) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.description = description;
        this.groupPhoto = groupPhoto;
        this.creatorUsername = creatorUsername;
        this.members = new ArrayList<>();
        this.admins = new ArrayList<>();
        this.removedMembers = new ArrayList<>();
        this.posts = new ArrayList<>();
        this.groupPeople = new ArrayList<>();
        this.requests = new ArrayList<>();
    }

    // Getters
    public String getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getDescription() {
        return description;
    }

    public String getGroupPhoto() {
        return groupPhoto;
    }

    public String getCreatorUsername() {
        return creatorUsername;
    }

    public List<GroupMember> getMembers() {
        return members;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGroupPhoto(String groupPhoto) {
        this.groupPhoto = groupPhoto;
    }

    public void setMembers(List<GroupMember> members) {
        this.members = members;
    }
    
    public void setRemovedMembers(List<GroupMember> removedMembers) {
        this.removedMembers = removedMembers;
    }

    public void setPosts(List<Post> posts) {
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
        return creatorUsername.equals(username);
    }
    
    // Check if removed member
    public boolean isRemovedMember(String username){
        for(GroupMember member: removedMembers){
            if (member.getUsername().equals(username))
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
    }
    
    // Send join request
    public void sendJoinRequest(String username){
        requests.add(username);
    }
    
    // Remove join request
    public void removeJoinRequest(String username){
        requests.remove(username);
    }
    
    // Get join requests
    public List<String> getJoinRequests(){
        return requests;
    }
    
    // Delete group from database
    public void deleteFromDatabase(){
        GroupsDatabase db = new GroupsDatabase();
        db.deleteGroup(groupId);
    }
    
    // Get all Group People
    public List<String> GetGroupPeople(){
        groupPeople.add(creatorUsername + " (Creator)");
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
        JSONArray groupsArray = db.loadGroups();

        JSONObject groupObj = new JSONObject();
        groupObj.put("groupId", this.groupId);
        groupObj.put("groupName", this.groupName);
        groupObj.put("description", this.description);
        groupObj.put("groupPhoto", this.groupPhoto);
        groupObj.put("creatorUsername", this.creatorUsername);
        
        JSONArray groupMembers = new JSONArray();
        for (GroupMember member : members) {
            JSONObject memberObj = new JSONObject();
            memberObj.put("userName", member.getUsername());
            memberObj.put("groupname", member.getGroupname());
            groupMembers.put(memberObj);
        }
        groupObj.put("members", groupMembers);
        
        JSONArray groupremovedMembers = new JSONArray();
        for (GroupMember member : removedMembers) {
            JSONObject memberObj = new JSONObject();
            memberObj.put("userName", member.getUsername());
            memberObj.put("groupname", member.getGroupname());
            groupremovedMembers.put(memberObj);
        }
        groupObj.put("removedMembers", groupremovedMembers);

        JSONArray groupPosts = new JSONArray();
        for (Post post : posts) {
            JSONObject postsObj = new JSONObject();
            postsObj.put("postId", post.getPostId());
            postsObj.put("username", post.getUsername());
            postsObj.put("groupname", post.getGroupname());
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


