/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connecthub;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author waelj
 */
public class GroupManagement {
    private List<Group> groups;
    private static GroupManagement admin;

    private GroupManagement() {
        this.groups = new ArrayList<>();
        loadGroups();
    }
    public static GroupManagement getAdmin()
    {
        if(admin==null)
        {
            admin=new GroupManagement();
        }
        return admin;
    }
    public List<Group> getGroups()
    {
        return groups;
    }
    private void saveGroups() {
        GroupDatabase database = new GroupDatabase();
        database.saveGroups(groups);
    }

    private void loadGroups() {
        GroupDatabase database = new GroupDatabase();
        this.groups = database.loadGroups();
    }
    public void updateGroup(Group g)
    {
        loadGroups();
        for(Group k : groups)
        {
            if(k.getGroupId().equals(g.getGroupId()))
            {
                g.setGroupName(k.getGroupName());
                g.setRemoved(k.getRemoved());
                g.setMembers(k.getMembers());
                g.setAdmins(k.getAdmins());
                g.setRequests(k.getRequests());
                g.setPhoto(k.getPhoto());
                g.setDiscription(k.getDiscription());
            }
        }
    }

}
