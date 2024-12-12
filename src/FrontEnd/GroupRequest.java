/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FrontEnd;


import connecthub.Group;
import connecthub.GroupAdmin;
import connecthub.GroupJoinRequests;
import connecthub.GroupManagement;
import connecthub.GroupRequests;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
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
public class GroupRequest extends JFrame {
    public GroupRequest(GroupAdmin admin ,Group group) {
    setTitle("Group Join Requests");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        GroupManagement mn=new GroupManagement(group.getGroupId(),group.getGroupName(),group.getDescription(),group.getGroupPhoto(),group.getCreatorUsername());
        GroupRequests req=new GroupRequests(mn);
        List<GroupJoinRequests> requests = req.getJoinRequest();
        
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
                    admin.approveJoinRequest(k.getUsername());
                    JOptionPane.showMessageDialog(GroupRequest.this, "Accepted");
                    mainPanel.remove(objectPanel);
                }
            });
            
            declineButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    admin.declineJoinRequest(k.getUsername());
                    JOptionPane.showMessageDialog(GroupRequest.this, "Declined");
                    mainPanel.remove(objectPanel);
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
