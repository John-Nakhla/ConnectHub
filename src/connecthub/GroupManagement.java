
package connecthub;


public class GroupManagement {
    
    private final String groupId;
    private String groupName;
    private String description;
    private String groupPhoto;
    private final String creatorUsername;

    public GroupManagement(String groupName, String description, String groupPhoto, String creatorUsername) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.description = description;
        this.groupPhoto = groupPhoto;
        this.creatorUsername = creatorUsername;
    }
    
    // Getters & Setters
    public String getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getDescription() {
        return description;
    }

    public String getGroupPhoto() {
        return groupPhoto;
    }

    public String getCreatorUsername() {
        return creatorUsername;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGroupPhoto(String groupPhoto) {
        this.groupPhoto = groupPhoto;
    }    
}
