/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connecthub;

/**
 *
 * @author waelj
 */
public class FriendRequestNotification extends NotificationDecorator {

    public FriendRequestNotification(Notification notification) {
        super(notification);
    }

    @Override
    public void handleAction(String action) {
        if ("accept".equalsIgnoreCase(action)) {
            System.out.println("Friend request accepted.");
        } else if ("decline".equalsIgnoreCase(action)) {
            System.out.println("Friend request declined.");
        } else {
            System.out.println("Unknown action for friend request.");
        }
    }
}
