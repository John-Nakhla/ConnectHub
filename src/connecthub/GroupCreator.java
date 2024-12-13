
package connecthub;

import java.util.Objects;


public class GroupCreator extends GroupAdmin{
    
    public GroupCreator(String username, String groupname) {
        super(username, groupname);
    }
    
    GroupsDatabase db = new GroupsDatabase();
    Group group = db.searchGroup(getGroupname());
    
    // Promote a member to admin
    public boolean promoteToAdmin(GroupMember member) {
        if (member == null || group == null) {
        System.out.println("Invalid member or group.");
        return false;
        }

        // Locate the exact member in the group
        GroupMember foundMember = null;
        for (GroupMember m : group.members) {
            if (m.equals(member)) {
                foundMember = m;
                break;
            }
        }

        if (foundMember == null) {
            System.out.println("Member not found in group members.");
            return false;
        }

        // Promote the member
        GroupAdmin admin = new GroupAdmin(foundMember.getUsername(), foundMember.getGroupname());
        group.removeMember(foundMember.getUsername());// Use exact reference
        group.addAdmin(admin);
        group.saveToFile(); // Save the changes
        return true;

    }

    // Demote an admin to a regular member
    public boolean demoteToMember(GroupAdmin admin) {

            GroupMember member = new GroupMember(admin.getUsername(), admin.getGroupname());
            group.admins.remove(admin);
            group.members.add(member);
            group.saveToFile();
            return true;
        
    }

    
}
