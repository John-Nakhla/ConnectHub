
package connecthub;

import java.util.List;


public class GroupMember{
    
    private final String username;
    private final String groupname;
    private PostsDatabase db;

    public GroupMember(String username, String groupname) {
        this.username = username;
        this.groupname = groupname;
        db = new PostsDatabase();
    }
    
    // Getters
    public String getGroupname() {
        return groupname;
    }

    public String getUsername() {
        return username;
    }

    
    // Post a content
    public void postContent(String content, String img){
        db.createPost(username, content, groupname, img);
    }
    
    // Leave group
    public void leaveGroup(String groupId){
        GroupsDatabase groups = new GroupsDatabase();
        Group group = groups.searchGroup(groupname);
        List<Post> posts = db.getMemberPostsInGroup(username, groupname);
        for(Post post: posts){
            db.deletePost(post.getPostId());
        }
        group.removeMember(username);
    }
}
