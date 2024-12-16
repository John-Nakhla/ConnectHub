
package connecthub;

import java.util.List;



public class GroupNotification extends NotificationDecorator {

    public GroupNotification(Notification notification) {
        super(notification);
    }
    Group group;
    List<User> all;
    List<User> moderators;
    
    @Override
    public void handleAction(String type, String sender, String message, String groupname){
        
        GroupsDatabase db = new GroupsDatabase();
        group = db.searchGroup(groupname);
        loadGroupPeople();
        switch (type) {
            case "forAll" -> {
                for(User user: all){
                    if(!user.getUserId().equals(sender))
                        this.send(user.getUserId(), groupname, message, "Gr");
                }
            }
            case "moderators" -> {
                for(User user: moderators){
                    if(!user.getUserId().equals(sender))
                        this.send(user.getUserId(), groupname, message, "Gr");
                }
            }
            case "forUser" -> this.send(sender, groupname, message, "Gr");
            default -> {
            }
        }
    }

    private void loadGroupPeople(){
        
        UsersDatabase udb = new UsersDatabase();
        all.add(udb.loadUserByName(group.getCreatorUsername()));
        moderators.add(udb.loadUserByName(group.getCreatorUsername()));
        
        for (GroupAdmin admin: group.getAdmins()){
            all.add(udb.loadUserByName(admin.getUsername()));
            moderators.add(udb.loadUserByName(admin.getUsername()));
        }
        
        for (GroupMember member: group.getMembers()){
            all.add(udb.loadUserByName(member.getUsername()));
        }
    }
}