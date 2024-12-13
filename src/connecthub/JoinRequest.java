/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connecthub;

/**
 *
 * @author waelj
 */
public class JoinRequest {
    private User sender;
    private Group reciever;
    public JoinRequest(User u , Group g)
    {
        this.sender = u;
        this.reciever =g;
    }
    public User getSender() {
        return sender;
    }

    public Group getReciever() {
        return reciever;
    }
    
}
