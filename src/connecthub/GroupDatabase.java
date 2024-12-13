/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connecthub;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author waelj
 */
public class GroupDatabase {
    private static final String FILE_PATH = "groups.json";
    private UsersDatabase usersDatabase = new UsersDatabase();
    // Load users from file 
    public void refresh(Group g) {
        List<Group> groups = this.loadGroups();
        List<Group> updatedGroups = new ArrayList<>(); 
        for (Group k : groups) 
        {
            if (k.getGroupId().equals(g.getGroupId())) {
                updatedGroups.add(g);
            }
            else
            {
                updatedGroups.add(k);
            }
        }
        this.saveGroups(updatedGroups);
    }
    public Group loadGroup(String groupId)
    {
        List<Group> groups = this.loadGroups();
        for(Group k : groups)
        {
            if(k.getGroupId().equals(groupId))
            {
                return k;
            }
        }
        return null;
    }
    public List<Group> loadGroups() {
        List<Group> groups = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            StringBuilder Data = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                Data.append(line);
            }
            JSONArray groupArray = new JSONArray(Data.toString());
            for (int i = 0; i < groupArray.length(); i++) {
                JSONObject groupJson = groupArray.getJSONObject(i);

                if (!groupJson.has("groupname") || !groupJson.has("groupdescription") || !groupJson.has("Creator")) {
                    continue;
                }
                User creator = usersDatabase.loadUser(groupJson.getString("Creator"));
                Group group = new Group(creator,groupJson.getString("groupname"),groupJson.getString("groupdescription"));
                group.setGroupId(groupJson.getString("groupId"));
                groups.add(group);
            }
            for (int i = 0; i < groupArray.length(); i++) {
                JSONObject groupJson = groupArray.getJSONObject(i);
                Group group = groups.get(i);
                JSONArray membersArray = groupJson.optJSONArray("members");
                if (membersArray != null && membersArray.length() > 0) {
                    for (int j = 0; j < membersArray.length(); j++) {
                        String memberId = membersArray.getString(j);
                        User member = usersDatabase.loadUser(memberId);
                        group.getMembers().add(member);
                    }
                }
                JSONArray adminsArray = groupJson.optJSONArray("admins");
                if (adminsArray != null && adminsArray.length() > 0) {
                    for (int j = 0; j < adminsArray.length(); j++) {
                        String adminId = adminsArray.getString(j);
                        User admin = usersDatabase.loadUser(adminId);
                        group.getAdmins().add(admin);
                    }
                }
                JSONArray joinRequestsSendersArray = groupJson.optJSONArray("JoinRequestSenders");
                if (joinRequestsSendersArray != null &&joinRequestsSendersArray.length() > 0) {
                    for (int j = 0; j < joinRequestsSendersArray.length(); j++) {
                        String SenderId = joinRequestsSendersArray.getString(j);
                        User sender = usersDatabase.loadUser(SenderId);
                        JoinRequest jr = new JoinRequest(sender,group);
                        group.getRequests().add(jr);
                    }
                }
                JSONArray removedMembersArray = groupJson.optJSONArray("removedMembers");

                if (removedMembersArray != null && removedMembersArray.length() > 0) {
                    for (int j = 0; j < removedMembersArray.length(); j++) {
                        String removedId = removedMembersArray.getString(j);
                        User removed = usersDatabase.loadUser(removedId);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
        return groups;
    }

    // save users to file 
    public void saveGroups(List<Group> groups) {
        JSONArray groupArray = new JSONArray();
        for (Group group : groups) {
            JSONObject groupJson = new JSONObject();
            groupJson.put("groupId", group.getGroupId());
            groupJson.put("groupname", group.getGroupName());
            groupJson.put("groupdescription",group.getDiscription());
            groupJson.put("Creator",group.getCreator().getUserId());
            JSONArray membersArray = new JSONArray();
            for (User member : group.getMembers()) {
                membersArray.put(member.getUserId());
            }
            groupJson.put("members", membersArray);
            JSONArray adminsArray = new JSONArray();
            for (User admin : group.getAdmins()) {
                adminsArray.put(admin.getUserId());
            }
            groupJson.put("admins", adminsArray);
            JSONArray joinRequestsArray = new JSONArray();
            for (JoinRequest request : group.getRequests()) {
                joinRequestsArray.put(request.getSender().getUserId());
            }
            groupJson.put("JoinRequestSenders", joinRequestsArray);
            JSONArray removedMembersArray = new JSONArray();
            for (User removed : group.getRemoved()) {
                removedMembersArray.put(removed.getUserId());
            }
            groupJson.put("removedMembers", removedMembersArray);
            groupJson.put("Photo", group.getPhoto());
            groupArray.put(groupJson);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write(groupArray.toString(4));
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }
}
