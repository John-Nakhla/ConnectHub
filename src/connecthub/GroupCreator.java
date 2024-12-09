
package connecthub;


public class GroupCreator extends GroupAdmin{
    
    public GroupCreator(String userId, String userName, String groupId) {
        super(userId, userName, groupId);
    }
    
    GroupsDatabase db = new GroupsDatabase();
    Group group = db.searchGroup(getGroupId());
    
    // Promote a member to admin
    public boolean promoteToAdmin(GroupMember member) {
        if (member instanceof GroupAdmin) {
            return false;
        } 
        else {
            GroupAdmin admin = new GroupAdmin(member.getUserId(), member.getUserName(), member.getGroupId());
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
            GroupMember member = new GroupMember(admin.getUserId(), admin.getUserName(), admin.getGroupId());
            group.admins.remove(admin);
            group.members.add(member);
            return true;
        }
    }
    
}
