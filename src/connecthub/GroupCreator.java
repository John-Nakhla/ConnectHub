
package connecthub;


public class GroupCreator extends GroupAdmin{
    
    public GroupCreator(String username, String groupname) {
        super(username, groupname);
    }
    
    GroupsDatabase db = new GroupsDatabase();
    Group group = db.searchGroup(getGroupname());
    
    // Promote a member to admin
    public boolean promoteToAdmin(GroupMember member) {
        if (member instanceof GroupAdmin) {
            return false;
        } 
        else {
            GroupAdmin admin = new GroupAdmin(member.getUsername(), member.getGroupname());
            group.members.remove(member);
            group.admins.add(admin);
            return true;
        }
    }

    // Demote an admin to a regular member
    public boolean demoteToMember(GroupAdmin admin) {
        if (admin instanceof GroupMember) {
            return false;
        } 
        else {
            GroupMember member = new GroupMember(admin.getUsername(), admin.getGroupname());
            group.admins.remove(admin);
            group.members.add(member);
            return true;
        }
    }
    
}
