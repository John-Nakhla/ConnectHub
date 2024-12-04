/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connecthub;

/**
 *
 * @author waelj
 */
public class Profile {
    private String profilePhoto; //path of image
    private String coverPhoto; //path for image
    private String bio;
    public void changeProfilePhoto(String path)
    {
        this.profilePhoto = path;
    }
    public void changeCoverPhoto(String path)
    {
        this.coverPhoto = path;
    }
    public void changeBio(String bio)
    {
        this.bio = bio;
    }
    
}
