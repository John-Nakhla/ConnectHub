/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FrontEnd;

import connecthub.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

/**
 *
 * @author John
 */
public class Notifications extends JPanel {

    private BasicNotification notification;
    private javax.swing.JLabel PostTextLbl;

    private static int yPosition = 10;

    /**
     * Creates new form Post
     *
     * @param notification
     */
    public Notifications(BasicNotification notification) {
        this.notification = notification;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        PostTextLbl = new javax.swing.JLabel(notification.getMessage());
        add(PostTextLbl);

        if ("FR".equals(notification.getType())) {
            List<FriendRequest> requests = notification.getOwner().getFriendRequests();

            JButton acceptBtn = new JButton("Accept");
            acceptBtn.addActionListener((ActionEvent e) -> {
                for (FriendRequest fr : requests) {
                    if (fr.getSender().getUserId().equals(notification.getSender()) && fr.getReceiver().getUserId().equals(notification.getOwner().getUserId())) {
                        notification.getOwner().acceptFriendRequest(fr);
                    }
                }
                notification.setStatus("Inactive");
            });

            JButton declineBtn = new JButton("Decline");
            declineBtn.addActionListener((ActionEvent e) -> {
                for (FriendRequest fr : requests) {
                    if (fr.getSender().getUserId().equals(notification.getSender()) && fr.getReceiver().getUserId().equals(notification.getOwner().getUserId())) {
                        notification.getOwner().declineFriendRequest(fr);
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
