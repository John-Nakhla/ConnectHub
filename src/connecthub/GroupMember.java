
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

    
    // Posts a content
    public void postContent(String content, String img){
        db.createPost(username, content, groupname, img);
    }
    
    // Leave group
    public void leaveGroup(String groupname){
        GroupsDatabase groups = new GroupsDatabase();
        Group group = groups.searchGroup(groupname);
        List<Posts> posts = db.getMemberPostsInGroup(username, groupname);
        for(Posts post: posts){
            db.deletePost(post.getPostId());
        }
        group.removeMember(username);
    }
}
