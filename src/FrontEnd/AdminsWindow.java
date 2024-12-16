
package FrontEnd;

import connecthub.*;
import connecthub.User;
import java.util.*;
import javax.swing.*;


public class AdminsWindow extends javax.swing.JFrame {
    private GroupWindow window;
    private Group group;
    private GroupAdmin admin;
    Notification notification;
    Notification groupNotification = new GroupNotification(notification);
    UsersDatabase db = new UsersDatabase();
    
    public AdminsWindow(GroupWindow window ,User user, Group group) {
        initComponents();
        this.group = group;
        this.admin = new GroupAdmin(user.getUsername(), group.getGroupName());
        this.window=window;
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        viewrequests = new javax.swing.JButton();
        removemembers = new javax.swing.JButton();
        manageposts = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        viewrequests.setBackground(new java.awt.Color(153, 102, 0));
        viewrequests.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        viewrequests.setForeground(new java.awt.Color(255, 255, 255));
        viewrequests.setText("View Group Requests");
        viewrequests.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewrequestsActionPerformed(evt);
            }
        });

        removemembers.setBackground(new java.awt.Color(153, 102, 0));
        removemembers.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        removemembers.setForeground(new java.awt.Color(255, 255, 255));
        removemembers.setText("Remove Members");
        removemembers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removemembersActionPerformed(evt);
            }
        });

        manageposts.setBackground(new java.awt.Color(153, 102, 0));
        manageposts.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        manageposts.setForeground(new java.awt.Color(255, 255, 255));
        manageposts.setText("Manage Posts");
        manageposts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                managepostsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(viewrequests, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(removemembers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(manageposts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(viewrequests)
                .addGap(18, 18, 18)
                .addComponent(removemembers)
                .addGap(26, 26, 26)
                .addComponent(manageposts)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 232, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 12, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 13, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 208, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 23, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 23, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void viewrequestsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewrequestsActionPerformed
        GroupsRequest g = new GroupsRequest(window,admin,group);
        g.setVisible(true);
        g.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_viewrequestsActionPerformed

    private void removemembersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removemembersActionPerformed
        if (group == null) {
            JOptionPane.showMessageDialog(this, "Group not found.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Get the list of non-admin members
        List<GroupMember> members = new ArrayList<>(group.getMembers());

        if (members.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No Members To remove.", "Error", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Prepare member list for selection
        String[] memberNames = new String[members.size()];
        for (int i = 0; i < members.size(); i++) {
            memberNames[i] = members.get(i).getUsername();
        }

        // Create the JList with the member names
        JList<String> memberList = new JList<>(memberNames);
        memberList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Wrap the JList in a JScrollPane for scrolling
        JScrollPane scrollPane = new JScrollPane(memberList);

        // Show dialog to select a member
        int result = JOptionPane.showConfirmDialog(
            this,
            scrollPane,
            "Select a member to remove:",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
        );

        // Process selection
        if (result == JOptionPane.OK_OPTION) {
            int selectedIndex = memberList.getSelectedIndex();
            if (selectedIndex != -1) {
                GroupMember selectedMember = members.get(selectedIndex);

                group.removeMember(selectedMember.getUsername());

                if (group.isRemovedMember(selectedMember.getUsername())) {
                    JOptionPane.showMessageDialog(this, "Member removed successfully.", "Removal Successful", JOptionPane.INFORMATION_MESSAGE);
                    group.saveToFile(); // Save changes to the file
                    groupNotification.handleAction("forUser", db.loadUserByName(selectedMember.getUsername()).getUserId(), group.getGroupName()+": You have been removed from the group", group.getGroupName());
                    window.reloadGroupDetails(); // Refresh the UI
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to remove member.", "Removal Failed", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "No member selected.", "Invalid Selection", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_removemembersActionPerformed

    private void managepostsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_managepostsActionPerformed
        ReviewPendingPostsAdmin p=new ReviewPendingPostsAdmin(admin,group,window);
        p.setVisible(true);
        p.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_managepostsActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton manageposts;
    private javax.swing.JButton removemembers;
    private javax.swing.JButton viewrequests;
    // End of variables declaration//GEN-END:variables
}
