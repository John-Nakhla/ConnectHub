
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
        GroupMember foundMember = null;
        for (GroupMember m : group.members) {
            if (m.getUsername().equals(member.getUsername())) { // Ensure `equals()` is used
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
        group.members.remove(foundMember); // Use found reference
        group.admins.add(admin);
        group.saveToFile();
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
