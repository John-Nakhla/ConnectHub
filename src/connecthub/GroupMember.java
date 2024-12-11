
package connecthub;

import java.util.List;


public class GroupMember{
    
    private final String userId;
    private final String userName;
    private final String groupId;
    private PostsDatabase db;

    public GroupMember(String userId, String userName, String groupId) {
        this.userId = userId;
        this.userName = userName;
        this.groupId = groupId;
        db = new PostsDatabase();
    }
    
    // Getters
    public String getUserId() {
        return userId;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getUserName() {
        return userName;
    }

    
    // Post a content
    public void postContent(String content, String img){
        db.createPost(userId, content, groupId, img);
    }
    
    // Leave group
    public void leaveGroup(String groupId){
        GroupsDatabase groups = new GroupsDatabase();
        Group group = groups.searchGroup(groupId);
        List<Posts> posts = db.getMemberPostsById(userId, groupId);
        for(Posts post: posts){
            db.deletePost(post.getPostId());
        }
        group.removeMember(userId);
    }
}
