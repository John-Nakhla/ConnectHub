
package connecthub;

import java.time.LocalDateTime;


public class GroupAdmin extends GroupMember{
    
    public GroupAdmin(String userName, String groupname) {
        super(userName, groupname);
    }
    
    // Remove member
    public void removeMember(GroupMember member){
        member.leaveGroup(getGroupname());
    }
    
    // Edit post
    public void editPost(String postId, String content, String img){
        PostsDatabase posts = new PostsDatabase();
        Posts post = posts.getPostById(postId);
        LocalDateTime time = post.getTimestamp();
        String username = post.getUsername();
        posts.deletePost(post.getPostId());
        Posts newPost = new Posts(postId, username, getGroupname(), content, img);
        newPost.setTimestamp(time.toString());
        newPost.saveToFile();
    }
    
    // Delete post
    public void deletePost(Posts post){
        PostsDatabase posts = new PostsDatabase();
        posts.deletePost(post.getPostId());
    }
    
    // Approve join request
    // Decline join request
    
}
