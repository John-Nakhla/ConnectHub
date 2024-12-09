
package connecthub;

import java.time.LocalDateTime;


public class GroupAdmin extends GroupMember{
    
    public GroupAdmin(String userId, String userName, String groupId) {
        super(userId, userName, groupId);
    }
    
    // Remove member
    public void removeMember(GroupMember member){
        member.leaveGroup(getGroupId());
    }
    
    // Edit post
    public void editPost(String postId, String content, String img){
        PostsDatabase posts = new PostsDatabase();
        Post post = posts.getPostById(postId);
        LocalDateTime time = post.getTimestamp();
        String author = post.getAuthorId();
        posts.deletePost(post.getPostId());
        Post newPost = new Post(postId, author, getGroupId(), content, img);
        newPost.setTimestamp(time.toString());
        newPost.saveToFile();
    }
    
    // Delete post
    public void deletePost(Post post){
        PostsDatabase posts = new PostsDatabase();
        posts.deletePost(post.getPostId());
    }
    
    // Approve join request
    // Decline join request
    
}
