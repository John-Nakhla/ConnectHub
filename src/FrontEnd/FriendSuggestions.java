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

    UsersDatabase database = new UsersDatabase();
    List<User> users = database.loadUsers();

    public FriendSuggestions(User u) {
        setTitle("Friend Suggestions");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        List<User> friends = u.getFriends();
        for (User k : users) {
            if (!friends.contains(k)) 
            {
                JPanel objectPanel = new JPanel(new BorderLayout());
                JLabel nameLabel = new JLabel("Name: " + k.getUsername());
                JPanel detailsPanel = new JPanel(new GridLayout(5, 5));
                detailsPanel.add(nameLabel);
                JButton profileButton = new JButton("Profile");
                JButton addButton = new JButton("Add");
                profileButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                    }
                });
                addButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        u.sendFriendRequest(k);
                        JOptionPane.showMessageDialog(FriendSuggestions.this, "Friend request sent");
                    }
                });
                JPanel buttonPanel = new JPanel();
                buttonPanel.add(profileButton);
                buttonPanel.add(addButton);
                objectPanel.add(detailsPanel, BorderLayout.CENTER);
                objectPanel.add(buttonPanel, BorderLayout.SOUTH);
                mainPanel.add(objectPanel);
            }
        }
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane);
    }
}
