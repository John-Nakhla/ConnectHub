
package FrontEnd;

import connecthub.Group;
import connecthub.GroupAdmin;
import connecthub.GroupJoinRequests;
import connecthub.GroupNotification;
import connecthub.Notification;
import connecthub.UsersDatabase;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;


public class GroupsRequest extends JFrame{
    
    Notification notification;
    Notification groupNotification = new GroupNotification(notification);
    UsersDatabase db = new UsersDatabase();
    
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
                    groupNotification.handleAction("forUser", db.loadUserByName(k.getUsername()).getUserId(), group.getGroupName()+": Your request join has been approved! you're now a part of this group", group.getGroupName());
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
                    groupNotification.handleAction("forUser", db.loadUserByName(k.getUsername()).getUserId(), group.getGroupName()+": Unfortunately, your request join has been declined", group.getGroupName());
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
