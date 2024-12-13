
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
//    public void approveJoinRequest(String username,GroupJoinRequests req){
//        GroupsDatabase db = new GroupsDatabase();
//        Group group = db.searchGroup(getGroupname());
//        group.acceptJoinRequest(username,req);
//        group.saveToFile();
//    }
//    
//    // Decline join request
//    public void declineJoinRequest(String username,GroupJoinRequests req){
//        GroupsDatabase db = new GroupsDatabase();
//        Group group = db.searchGroup(getGroupname());
//        group.removeJoinRequest(req);
//        group.saveToFile();
//    }
    
    public void rejectPost(String postId) {
        PostsDatabase posts = new PostsDatabase();
        posts.rejectPost(postId);
    }
    
    public void approvePost(String postId) {
        PostsDatabase posts = new PostsDatabase();
         posts.approvePost(postId);
    }
}
