/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FrontEnd;

import connecthub.Group;
import connecthub.GroupAdmin;
import connecthub.GroupJoinRequests;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;

/**
 *
 * @author ADMIN
 */
public class GroupsRequest extends JFrame{
    public GroupsRequest(GroupWindow window,GroupAdmin admin ,Group group) {
    setTitle("Group Join Requests");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        List<GroupJoinRequests> requests = group.getRequests();
        
        for (GroupJoinRequests k : requests) {
            JPanel objectPanel = new JPanel(new BorderLayout());
            JLabel nameLabel = new JLabel("Name: " + k.getUsername());
            JPanel detailsPanel = new JPanel(new GridLayout(5,5));
            detailsPanel.add(nameLabel);
            JButton acceptButton = new JButton("Accept");
            JButton declineButton = new JButton("Decline");
            
            acceptButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    group.acceptJoinRequest(k.getUsername(),k);
                    group.saveToFile();
                    JOptionPane.showMessageDialog(GroupsRequest.this, "Accepted");
                    mainPanel.remove(objectPanel);
                    mainPanel.repaint();
                    window.reloadGroupDetails();
                }
            });
            
            declineButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    group.removeJoinRequest(k);
                    JOptionPane.showMessageDialog(GroupsRequest.this, "Declined");
                    group.saveToFile();
                    mainPanel.remove(objectPanel);
                    mainPanel.repaint();
                    window.reloadGroupDetails();
                }
            });
            JPanel buttonPanel = new JPanel();
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
