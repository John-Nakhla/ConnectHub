/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package FrontEnd;

import FrontEnd.FriendRequests;
import FrontEnd.Profile;
import connecthub.FriendRequest;
import connecthub.GroupAdmin;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author ADMIN
 */
public class GroupRequest extends javax.swing.JPanel {

    /**
     * Creates new form GroupRequest
     */
    public GroupRequest(GroupAdmin admin) {
        setTitle("Group Requests");
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents



    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

