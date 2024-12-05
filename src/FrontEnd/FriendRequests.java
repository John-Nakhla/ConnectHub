/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FrontEnd;
import javax.swing.*;
import connecthub.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
/**
 *
 * @author waelj
 */
public class FriendRequests extends JFrame {
     public FriendRequests(User u) {
        setTitle("Friend Requests");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        List<FriendRequest> requests = u.getFriendRequests();
        for (FriendRequest k : requests) {
            JPanel objectPanel = new JPanel(new BorderLayout());
            JLabel nameLabel = new JLabel("Name: " + k.getSender().getUsername());
            JPanel detailsPanel = new JPanel(new GridLayout(5,5));
            detailsPanel.add(nameLabel);
            JButton profileButton = new JButton("Profile");
            JButton acceptButton = new JButton("Accept");
            JButton declineButton = new JButton("Decline");
            profileButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Profile p = new Profile(k.getSender());
                    p.setVisible(true);
                }
            });
            acceptButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    u.acceptFriendRequest(k);
                    JOptionPane.showMessageDialog(FriendRequests.this, "Accepted");
                    mainPanel.remove(objectPanel);
                }
            });
            declineButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    u.declineFriendRequest(k);
                    JOptionPane.showMessageDialog(FriendRequests.this, "Declined");
                    mainPanel.remove(objectPanel);
                }
            });
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(profileButton);
            buttonPanel.add(acceptButton);
            buttonPanel.add(declineButton);
            objectPanel.add(detailsPanel, BorderLayout.CENTER);
            objectPanel.add(buttonPanel, BorderLayout.SOUTH);
            mainPanel.add(objectPanel);
        }
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane);
    }
}
