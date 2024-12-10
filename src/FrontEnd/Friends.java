/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FrontEnd;

import connecthub.User;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;

/**
 *
 * @author waelj
 */
public class Friends extends JFrame {

    public Friends(User u) {
        setTitle("Friends");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        List<User> friends = u.getFriends();
        for (User k : friends) {
            JPanel objectPanel = new JPanel(new BorderLayout());
            JLabel nameLabel = new JLabel("Name: " + k.getUsername());
            String status;
            if (k.isStatus()) {
                status = "Online";
            } else {
                status = "Offline";
            }
            JLabel statusLabel = new JLabel(status);
            JPanel detailsPanel = new JPanel(new GridLayout(5, 5));
            detailsPanel.add(nameLabel);
            detailsPanel.add(statusLabel);
            JButton profileButton = new JButton("Profile");
            JButton removeButton = new JButton("Remove");
            JButton blockButton = new JButton("Block");
            profileButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Profile p = new Profile(k, false);
                    p.setVisible(true);
                }
            });
            removeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    u.removeFriend(k);
                    JOptionPane.showMessageDialog(Friends.this, "Removed");
                    mainPanel.remove(objectPanel);
                }
            });
            blockButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    u.blockUser(k);
                    JOptionPane.showMessageDialog(Friends.this, "Blocked");
                    mainPanel.remove(objectPanel);
                }
            });
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(profileButton);
            buttonPanel.add(removeButton);
            buttonPanel.add(blockButton);
            objectPanel.add(detailsPanel, BorderLayout.CENTER);
            objectPanel.add(buttonPanel, BorderLayout.SOUTH);
            mainPanel.add(objectPanel);
        }
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane);
    }
}
