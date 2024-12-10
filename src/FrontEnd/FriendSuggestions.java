/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FrontEnd;

import connecthub.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author waelj
 */
public class FriendSuggestions extends JFrame {
    public FriendSuggestions(User u) {
        u.update();
        setTitle("Friend Suggestions");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        List<User> suggestions = u.friendsOfFriends();
        for (User k : suggestions) {
            JPanel objectPanel = new JPanel(new BorderLayout());
            JLabel nameLabel = new JLabel("Name: " + k.getUsername());
            JPanel detailsPanel = new JPanel(new GridLayout(5, 5));
            detailsPanel.add(nameLabel);
            JButton profileButton = new JButton("Profile");
            JButton addButton = new JButton("Add");
            profileButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Profile p = new Profile(k, false);
                    p.setVisible(true);
                }
            });
            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    u.sendFriendRequest(k);
                    JOptionPane.showMessageDialog(FriendSuggestions.this, "Friend request sent");
                    mainPanel.remove(objectPanel);
                }
            });
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(profileButton);
            buttonPanel.add(addButton);
            objectPanel.add(detailsPanel, BorderLayout.CENTER);
            objectPanel.add(buttonPanel, BorderLayout.SOUTH);
            mainPanel.add(objectPanel);
        }
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane);
    }
}
