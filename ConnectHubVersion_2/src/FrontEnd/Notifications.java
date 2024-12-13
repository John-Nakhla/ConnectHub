
package FrontEnd;

import connecthub.*;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;


public class Notifications extends JPanel {

    private BasicNotification notification;
    private javax.swing.JLabel PostTextLbl;

    private static int yPosition = 10;
    User user;
    UsersDatabase db = new UsersDatabase();
  
    public Notifications(BasicNotification notification) {
        this.notification = notification;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        PostTextLbl = new javax.swing.JLabel(notification.getMessage());
        add(PostTextLbl);

        if ("FR".equals(notification.getType())) {
            user = db.loadUser(notification.getOwner());
            List<FriendRequest> requests = user.getFriendRequests();

            JButton acceptBtn = new JButton("Accept");
            acceptBtn.addActionListener((ActionEvent e) -> {
                for (FriendRequest fr : requests) {
                    if (fr.getSender().getUserId().equals(notification.getSender()) && fr.getReceiver().getUserId().equals(notification.getOwner())) {
                        user = db.loadUser(notification.getOwner());
                        user.acceptFriendRequest(fr);
                    }
                }
                notification.setStatus("Inactive");
            });

            JButton declineBtn = new JButton("Decline");
            declineBtn.addActionListener((ActionEvent e) -> {
                for (FriendRequest fr : requests) {
                    if (fr.getSender().getUserId().equals(notification.getSender()) && fr.getReceiver().getUserId().equals(notification.getOwner())) {
                        user = db.loadUser(notification.getOwner());
                        user.declineFriendRequest(fr);
                    }
                }
                notification.setStatus("Inactive");
            });
        }

        yPosition += getHeight() + 10;
//        setMaximumSize(new Dimension(550, getPreferredSize().height));
        setVisible(true);
    }
}
