
package connecthub;


public class GroupCreator extends GroupAdmin{
    
    public GroupCreator(String username, String groupname) {
        super(username, groupname);
    }
    
    GroupsDatabase db = new GroupsDatabase();
    Group group = db.searchGroup(getGroupname());
    
    // Promote a member to admin
    public boolean promoteToAdmin(GroupMember member) {
        
        System.out.println("in promote");
        if (member == null || group == null) {
            System.out.println("Invalid member or group.");
            return false;
        }

        // Locate the exact member in the group
        GroupMember foundMember = null;
        for (GroupMember m : group.getMembers()) {
            if (m.getUsername().equals(member.getUsername())) {
                foundMember = m;
                System.out.println("found");
                break;
            }
        }

        if (foundMember == null) {
            System.out.println("Member not found in group members.");
            return false;
        }

        // Promote the member
        GroupAdmin admin = new GroupAdmin(foundMember.getUsername(), foundMember.getGroupname());
        group.addAdmin(admin);
        group.saveToFile();
        
        group.removeMember(foundMember.getUsername()); // Use exact reference
        System.out.println("removed member");
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
    public void rejectPost(String postId) {
        PostsDatabase posts = new PostsDatabase();
        posts.rejectPost(postId);
    }
    
    public void approvePost(String postId) {
        PostsDatabase posts = new PostsDatabase();
         posts.approvePost(postId);
    }

    
}
