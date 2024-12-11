
package connecthub;

import java.util.*;
import org.json.*;

public class Group {
    private final String groupId;
    private String groupName;
    private String description;
    private String groupPhoto;
    private final String creatorId;
    public List<GroupMember> members;
    public List<GroupAdmin> admins;
    private List<GroupMember> removedMembers;
    private List<Posts> posts;

    public Group(String groupId, String groupName, String description, String groupPhoto, String creatorId) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.description = description;
        this.groupPhoto = groupPhoto;
        this.creatorId = creatorId;
        this.members = new ArrayList<>();
        this.admins = new ArrayList<>();
        this.removedMembers = new ArrayList<>();
        this.posts = new ArrayList<>();
    }

    // Getters
    public String getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public List<GroupAdmin> getAdmins() {
        return admins;
    }
    

    public String getDescription() {
        return description;
    }

    public String getGroupPhoto() {
        return groupPhoto;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public List<GroupMember> getMembers() {
        return members;
    }

    public List<Posts> getPosts() {
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

    public void setPosts(List<Posts> posts) {
        this.posts = posts;
    }
    
    // Check if member
    public boolean isMember(String userId){
        for(GroupMember member: members){
            if (member.getUserId().equals(userId))
                return true;
        }
        return false;
    }
    
    // Check if removed member
    public boolean isRemovedMember(String userId){
        for(GroupMember member: removedMembers){
            if (member.getUserId().equals(userId))
                return true;
        }
        
        return false;
    }
    
    // Add a member
    public void addMember(GroupMember member) {
        members.add(member);
    }

    // Remove a member
    public void removeMember(String userId) {
        for (GroupMember member:members){
            if(member.getUserId().equals(userId)){
                removedMembers.add(member);
                members.remove(member);
                break;
            }
        }
        for(GroupAdmin admin: admins){
            if(admin.getUserId().equals(userId)){
                removedMembers.add(admin);
                admins.remove(admin);
                break;   
            }
        }
    }
    
    // Delete group from database
    public void deleteFromDatabase(){
        GroupsDatabase db = new GroupsDatabase();
        db.deleteGroup(groupId);
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
        groupObj.put("creatorId", this.creatorId);
        
        JSONArray groupMembers = new JSONArray();
        for (GroupMember member : members) {
            JSONObject memberObj = new JSONObject();
            memberObj.put("userId", member.getUserId());
            memberObj.put("userName", member.getUserName());
            memberObj.put("groupId", member.getGroupId());
            groupMembers.put(memberObj);
        }
        groupObj.put("members", groupMembers);
        
        JSONArray groupremovedMembers = new JSONArray();
        for (GroupMember member : removedMembers) {
            JSONObject memberObj = new JSONObject();
            memberObj.put("userId", member.getUserId());
            memberObj.put("userName", member.getUserName());
            memberObj.put("groupId", member.getGroupId());
            groupremovedMembers.put(memberObj);
        }
        groupObj.put("removedMembers", groupremovedMembers);

        JSONArray groupPosts = new JSONArray();
        for (Posts post : posts) {
            JSONObject postsObj = new JSONObject();
            postsObj.put("postId", post.getPostId());
            postsObj.put("authorId", post.getAuthorId());
            postsObj.put("groupId", post.getGroupId());
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


