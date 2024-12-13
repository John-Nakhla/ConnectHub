/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connecthub;
import java.util.*;

/**
 *
 * @author waelj
 */
public class Group {
    private String groupId;
    private String groupName;
    private String discription;
    private User creator;
    private List<User> members = new ArrayList<>();
    private List<User> admins = new ArrayList<>();
    private List<User> removed = new ArrayList<>();
    private List<Posts> posts = new ArrayList<>();
    private List<JoinRequest> requests = new ArrayList<>();
    private String photo =null;
    private int counter  =19999;
    private GroupDatabase database = new GroupDatabase();
    public Group(User creator, String name, String discription)
    {
        this.creator = creator;
        this.groupName = name;
        this.discription = discription;
        this.groupId =String.valueOf(counter++);
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
        database.refresh(this);
        
    }

    public void setDiscription(String discription) {
        this.discription = discription;
        database.refresh(this);
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public void setAdmins(List<User> admins) {
        this.admins = admins;
    }

    public void setRemoved(List<User> removed) {
        this.removed = removed;
    }

    public void setRequests(List<JoinRequest> requests) {
        this.requests = requests;
    }
    public void addMember(User u)
    {
        this.members.add(u);
        database.refresh(this);
    }
    public void removeMember(User u)
    {
        this.removed.add(u);
        this.members.remove(u);
        database.refresh(this);
    }
    public boolean isMember(User u)
    {
        for(User k : members)
        {
            if(k.getUserId().equals(u.getUserId()))
            {
                return true;
            }
        }
        return false;
    }
    public boolean isAdmin(User u)
    {
        for(User k : admins)
        {
            if(k.getUserId().equals(u.getUserId()))
            {
                return true;
            }
        }
        return false;
    }
    public boolean isRemovedMember(User u)
    {
        for(User k : removed)
        {
            if(k.getUserId().equals(u.getUserId()))
            {
                return true;
            }
        }
        return false;
    }
    public boolean isRequested(User u)
    {
        for(JoinRequest k : requests)
        {
            if(k.getSender().getUserId().equals(u.getUserId()))
            {
                return true;
            }
        }
        return false;
    }
    public String getGroupId()
    {
        return this.groupId;
    }
    public String getGroupName()
    {
        return this.groupName;
    }

    public String getDiscription() {
        return discription;
    }

    public List<User> getMembers() {
        return members;
    }

    public List<User> getAdmins() {
        return admins;
    }

    public List<User> getRemoved() {
        return removed;
    }

    public List<JoinRequest> getRequests() {
        return requests;
    }
    
    public User getCreator()
    {
        return this.creator;
    }
    public void recieveJoinRequest(JoinRequest j)
    {
        this.requests.add(j);
        database.refresh(this);
    }
    public void acceptRequest(JoinRequest j)
    {
        this.requests.remove(j);
        this.members.add(j.getSender());
        database.refresh(this);
    }
    public void declineRequest(JoinRequest j)
    {
        this.requests.remove(j);
        database.refresh(this);
    }
    public void setPhoto(String path)
    {
        this.photo = path;
        database.refresh(this);
    }
    public String getPhoto()
    {
         return this.photo;
    }
    public void update()
    {
        GroupManagement.getAdmin().updateGroup(this);
    }
}
