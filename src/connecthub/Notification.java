/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connecthub;

/**
 *
 * @author waelj
 */
public class Notification {
    User reciever;
    private int notificationId;
    public Notification(User reciever)
    {
        this.reciever = reciever;
    }
    public User getReciever()
    {
        return this.reciever;
    }
}
