
package connecthub;

import java.io.*;
import java.util.*;
import org.json.*;

public class GroupsDatabase {
    
    private final String FILE_PATH = "groups.json";
    private List<Group> groups = convertJsonArrayToGroupList(loadGroups());


    // Load groups from file
    public JSONArray loadGroups() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            StringBuilder contentBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line);
            }
            return new JSONArray(contentBuilder.toString());
        } catch (IOException e) {
            System.out.println("Error loading groups: " + e.getMessage());
            return new JSONArray();
        }
    }

    // Save groups to file
    public void saveGroups(JSONArray contentArray) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            writer.write(contentArray.toString(4));
        } catch (IOException e) {
            System.out.println("Error saving groups: " + e.getMessage());
        }
    }
    
    // Create a new group
    public Group createGroup(String groupName, String description, String groupPhoto, String creatorUsername) {
        String groupId = String.valueOf(uniqueId());
        Group group = new Group(groupId, groupName, description, groupPhoto, creatorUsername);
        group.saveToFile();
        return group;
    }
    
    private int uniqueId() {
        JSONArray postsArray = loadGroups();
        int nextId = 40000;

        if (postsArray.length() > 0) {
            JSONObject lastGroup = postsArray.getJSONObject(0);
            nextId = lastGroup.getInt("groupId") + 1;
        }
        return nextId;
    }

    // Delete a group
    public boolean deleteGroup(String groupId) {
        JSONArray groupsArray = loadGroups();
        boolean groupFound = false;
        PostsDatabase pdb = new PostsDatabase();
        List<Posts> posts;

        for (int i = 0; i < groupsArray.length(); i++) {
            JSONObject group = groupsArray.getJSONObject(i);
            if (group.getString("groupId").equals(groupId)) {
                posts = pdb.getGroupPosts(group.getString("groupName"));
                for(Posts post: posts){
                    pdb.deletePost(post.getPostId());
                }
                groupsArray.remove(i);
                groupFound = true;
                break;
            }
        }

        if (groupFound) {
            saveGroups(groupsArray);
            return true;
        } else {
            return false;
        }
    }

    // Search for a group by ID
    public Group searchGroup(String groupName) {
        for (Group group : groups) {
            if (group.getGroupName().equals(groupName)) {
                return group;
            }
        }
        return null;
    }

    // List all groups
    public List<Group> getGroups() {
        return groups;
    }
    
    // Groups suggestions
    public List<Group> getGroupsSuggestions(String username){
        List<Group> suggestions = new ArrayList<>();
        for(Group group: groups){
            if(!group.isMember(username) || !group.isAdmin(username) || !group.isCreator(username) || !group.isRemovedMember(username) )
                suggestions.add(group);
        }
        return suggestions;
    }

    // My Groups
    public List<Group> myGroups(String username){
        List<Group> mygroups = new ArrayList<>();
        for(Group group: groups){
            if(group.isMember(username) || group.isAdmin(username) || group.isCreator(username))
                mygroups.add(group);
        }
        return mygroups;
    }
    
    //Helping method to convert JsonArray to Group List
    public List<Group> convertJsonArrayToGroupList(JSONArray jsonArray) {
        List<Group> groupsList = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject groupJson = jsonArray.getJSONObject(i);

            Group group = new Group(
                groupJson.getString("groupId"),
                groupJson.getString("groupName"),
                groupJson.getString("description"),
                groupJson.getString("groupPhoto"),
                groupJson.getString("creatorUsername")
            );

            // Add members to the group
            JSONArray membersArray = groupJson.optJSONArray("members");
            List<GroupMember> membersList = new ArrayList<>();
            if (membersArray != null) {
                for (int j = 0; j < membersArray.length(); j++) {
                    JSONObject memberJson = membersArray.getJSONObject(j);
                    GroupMember member = new GroupMember(
                        memberJson.getString("userName"),
                        memberJson.getString("groupName")
                    );
                    group.addMember(member);
                }
                
            }
            
            // Add removed members to the group
            JSONArray removedMembersArray = groupJson.optJSONArray("removedMembers");
            List<GroupMember> removedMembersList = new ArrayList<>();
            if (removedMembersArray != null) {
                for (int j = 0; j < removedMembersArray.length(); j++) {
                    JSONObject memberJson = removedMembersArray.getJSONObject(j);
                    GroupMember member = new GroupMember(
                        memberJson.getString("userName"),
                        memberJson.getString("groupName")
                    );
                    removedMembersList.add(member);
                }
                group.setRemovedMembers(removedMembersList);
            }
            
            // Add join requests to the group
            JSONArray requestsArray = groupJson.optJSONArray("joinRequests");
            List<GroupJoinRequests> requests = new ArrayList<>();
            if (requestsArray != null) {
                for (int j = 0; j < requestsArray.length(); j++) {
                    JSONObject requestJson = requestsArray.getJSONObject(j);
                    GroupJoinRequests req = new GroupJoinRequests(
                        requestJson.getString("userId"),
                        requestJson.getString("username"),
                         requestJson.getString("groupId")   
                    );
                    group.addJoinRequest(req);
                }
                
            }

            // Add posts to the group
            JSONArray postsArray = groupJson.optJSONArray("posts");
            List<Posts> postsList = new ArrayList<>();
            if (postsArray != null) {
                for (int j = 0; j < postsArray.length(); j++) {
                    JSONObject postJson = postsArray.getJSONObject(j);
                    Posts post = new Posts(
                        postJson.getString("postId"),
                        postJson.getString("userName"),
                        postJson.getString("groupId"),
                        postJson.getString("content"),
                        postJson.optString("img", "")
                    );
                    post.setTimestamp(postJson.getString("timestamp"));
                    postsList.add(post);
                }
                group.setPosts(postsList);
            }
            
            groupsList.add(group);
        }

        return groupsList;
    }
}
