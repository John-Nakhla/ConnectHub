
package FrontEnd;

import connecthub.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;


public class PrimaryAdminWindow extends javax.swing.JPanel {
    private GroupWindow window;
     GroupCreator creator;
     Group group;
     
    public PrimaryAdminWindow(GroupWindow window,User user, Group group) {
        initComponents();
        this.window=window;
        this.group = group;
        this.creator = new GroupCreator(user.getUsername(), group.getGroupName());
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PromoteMember = new javax.swing.JButton();
        DemoteMember = new javax.swing.JButton();
        RemoveMember = new javax.swing.JButton();
        ManagePosts = new javax.swing.JButton();
        deletegroup = new javax.swing.JButton();
        changecoverphoto = new javax.swing.JButton();
        changename = new javax.swing.JButton();
        changedescription = new javax.swing.JButton();

        PromoteMember.setBackground(new java.awt.Color(153, 102, 0));
        PromoteMember.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        PromoteMember.setForeground(new java.awt.Color(255, 255, 255));
        PromoteMember.setText("Promote Member");
        PromoteMember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PromoteMemberActionPerformed(evt);
            }
        });

        DemoteMember.setBackground(new java.awt.Color(153, 102, 0));
        DemoteMember.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        DemoteMember.setForeground(new java.awt.Color(255, 255, 255));
        DemoteMember.setText("Demote Member");
        DemoteMember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DemoteMemberActionPerformed(evt);
            }
        });

        RemoveMember.setBackground(new java.awt.Color(153, 102, 0));
        RemoveMember.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        RemoveMember.setForeground(new java.awt.Color(255, 255, 255));
        RemoveMember.setText("Remove Member");
        RemoveMember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveMemberActionPerformed(evt);
            }
        });

        ManagePosts.setBackground(new java.awt.Color(153, 102, 0));
        ManagePosts.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ManagePosts.setForeground(new java.awt.Color(255, 255, 255));
        ManagePosts.setText("Manage Posts");
        ManagePosts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ManagePostsActionPerformed(evt);
            }
        });

        deletegroup.setBackground(new java.awt.Color(204, 0, 51));
        deletegroup.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        deletegroup.setText("Delete Group");
        deletegroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletegroupActionPerformed(evt);
            }
        });

        changecoverphoto.setBackground(new java.awt.Color(153, 102, 0));
        changecoverphoto.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        changecoverphoto.setForeground(new java.awt.Color(255, 255, 255));
        changecoverphoto.setText("Change Group Cover Photo");
        changecoverphoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changecoverphotoActionPerformed(evt);
            }
        });

        changename.setBackground(new java.awt.Color(153, 102, 0));
        changename.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        changename.setForeground(new java.awt.Color(255, 255, 255));
        changename.setText("Change Group Name");
        changename.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changenameActionPerformed(evt);
            }
        });

        changedescription.setBackground(new java.awt.Color(153, 102, 0));
        changedescription.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        changedescription.setForeground(new java.awt.Color(255, 255, 255));
        changedescription.setText("Change Group Description");
        changedescription.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changedescriptionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(DemoteMember, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(RemoveMember, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PromoteMember, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ManagePosts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(deletegroup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(changecoverphoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(changename, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(changedescription, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(PromoteMember)
                .addGap(18, 18, 18)
                .addComponent(DemoteMember)
                .addGap(18, 18, 18)
                .addComponent(RemoveMember)
                .addGap(18, 18, 18)
                .addComponent(ManagePosts)
                .addGap(18, 18, 18)
                .addComponent(changecoverphoto)
                .addGap(18, 18, 18)
                .addComponent(changename)
                .addGap(18, 18, 18)
                .addComponent(changedescription)
                .addGap(18, 18, 18)
                .addComponent(deletegroup)
                .addContainerGap(40, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void DemoteMemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DemoteMemberActionPerformed
    
    if (group == null) {
        JOptionPane.showMessageDialog(this, "Group not found.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Prepare a list of current admins (excluding the creator)
    List<GroupAdmin> admins = group.getAdmins();

    if (admins.isEmpty()) {
        JOptionPane.showMessageDialog(this, "No eligible admins to demote.", "Error", JOptionPane.INFORMATION_MESSAGE);
        return;
    }

    // Prepare admin list for selection
    String[] adminNames = new String[admins.size()];
    for (int i = 0; i < admins.size(); i++) {
        adminNames[i] = admins.get(i).getUsername();
    }

    JList<String> adminList = new JList<>(adminNames);
    adminList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    JScrollPane scrollPane = new JScrollPane(adminList);

    // Show dialog to select an admin
    int result = JOptionPane.showConfirmDialog(
        this,
        scrollPane,
        "Select an admin to demote to member:",
        JOptionPane.OK_CANCEL_OPTION,
        JOptionPane.PLAIN_MESSAGE
    );

    // Process selection
    if (result == JOptionPane.OK_OPTION) {
        int selectedIndex = adminList.getSelectedIndex();
        if (selectedIndex != -1) {
            GroupAdmin selectedAdmin = admins.get(selectedIndex);

            // Demote the selected admin
            boolean isDemoted = creator.demoteToMember(selectedAdmin);

            if (isDemoted) {
                JOptionPane.showMessageDialog(this, "Admin demoted to member successfully.", "Demotion Successful", JOptionPane.INFORMATION_MESSAGE);
                group.saveToFile(); // Save changes to the file
               window.reloadGroupDetails(); // Refresh the UI
            } else {
                JOptionPane.showMessageDialog(this, "Failed to demote admin. User may already be a member.", "Demotion Failed", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No admin selected.", "Invalid Selection", JOptionPane.WARNING_MESSAGE);
        }
    }
    }//GEN-LAST:event_DemoteMemberActionPerformed

    private void PromoteMemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PromoteMemberActionPerformed
    if (group == null) {
           JOptionPane.showMessageDialog(this, "Group not found.", "Error", JOptionPane.ERROR_MESSAGE);
           return;
       }

       // Get the list of non-admin members
       List<GroupMember> nonAdminMembers = group.getMembers();

       if (nonAdminMembers.isEmpty()) {
           JOptionPane.showMessageDialog(this, "No eligible members to promote.", "Error", JOptionPane.INFORMATION_MESSAGE);
           return;
       }

        // Prepare member list for selection
        String[] memberNames = new String[nonAdminMembers.size()];
        for (int i = 0; i < nonAdminMembers.size(); i++) {
            memberNames[i] = nonAdminMembers.get(i).getUsername();
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
            "Select a member to promote to admin:",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
        );

        // Process selection
        if (result == JOptionPane.OK_OPTION) {
        int selectedIndex = memberList.getSelectedIndex();
        if (selectedIndex != -1) {
            GroupMember selectedMember = nonAdminMembers.get(selectedIndex);

            // Promote the selected member
            boolean isPromoted = creator.promoteToAdmin(selectedMember);

            if (isPromoted) {
                JOptionPane.showMessageDialog(this, "Member promoted to admin successfully.", "Promotion Successful", JOptionPane.INFORMATION_MESSAGE);
                 group.saveToFile(); // Save changes to the file
                 window.reloadGroupDetails();// Refresh the UI
            } else {
                JOptionPane.showMessageDialog(this, "Failed to promote member. User may already be an admin.", "Promotion Failed", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No member selected.", "Invalid Selection", JOptionPane.WARNING_MESSAGE);
        }
    }
            }//GEN-LAST:event_PromoteMemberActionPerformed

    private void RemoveMemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveMemberActionPerformed
        if (group == null) {
           JOptionPane.showMessageDialog(this, "Group not found.", "Error", JOptionPane.ERROR_MESSAGE);
           return;
       }

       // Get the list of non-admin members
        List<GroupMember> allMembers = new ArrayList<>(group.getMembers());
        
        for(GroupAdmin admin: group.getAdmins() ){
            allMembers.add(admin);
        }
       if (allMembers.isEmpty()) {
           JOptionPane.showMessageDialog(this, "No Members To remove.", "Error", JOptionPane.INFORMATION_MESSAGE);
           return;
       }

    // Prepare member list for selection
        String[] memberNames = new String[allMembers.size()];
        for (int i = 0; i < allMembers.size(); i++) {
            memberNames[i] = allMembers.get(i).getUsername();
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
            GroupMember selectedMember = allMembers.get(selectedIndex);

            
            group.removeMember(selectedMember.getUsername());
            group.saveToFile();
            window.reloadGroupDetails();
            if (group.isRemovedMember(selectedMember.getUsername())) {
                JOptionPane.showMessageDialog(this, "Member removed successfully.", "Removal Successful", JOptionPane.INFORMATION_MESSAGE);
                group.saveToFile(); // Save changes to the file
               // reloadGroupDetails(); // Refresh the UI
            } else {
                JOptionPane.showMessageDialog(this, "Failed to remove member.", "Removal Failed", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No member selected.", "Invalid Selection", JOptionPane.WARNING_MESSAGE);
        }
    }
    }//GEN-LAST:event_RemoveMemberActionPerformed

    private void ManagePostsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ManagePostsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ManagePostsActionPerformed

    private void changecoverphotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changecoverphotoActionPerformed
        // Open file chooser to select new cover photo
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a new Cover Photo");
        
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String newCoverPhotoPath = selectedFile.getAbsolutePath();
            
            // Update the user's cover photo
            group.setGroupPhoto(newCoverPhotoPath); 
            group.saveToFile();
            window.reloadGroupDetails();
            
        }
    }//GEN-LAST:event_changecoverphotoActionPerformed

    private void changenameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changenameActionPerformed
        String name = JOptionPane.showInputDialog(this, "Enter New Group Name:");
        if (name != null && !name.trim().isEmpty()) {
            group.setGroupName(name);
            JOptionPane.showMessageDialog(this, "Group Name updated successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Group Name cannot be empty.");
        }
        group.saveToFile();
        window.reloadGroupDetails();
    }//GEN-LAST:event_changenameActionPerformed

    private void changedescriptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changedescriptionActionPerformed
        String description = JOptionPane.showInputDialog(this, "Enter New Group Description:");
        if (description != null && !description.trim().isEmpty()) {
            group.setDescription(description);
            JOptionPane.showMessageDialog(this, "Group Description updated successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Group Description cannot be empty.");
        }
        group.saveToFile();
        window.reloadGroupDetails();
    }//GEN-LAST:event_changedescriptionActionPerformed

    private void deletegroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletegroupActionPerformed

        if (group == null) {
            JOptionPane.showMessageDialog(this, "Group not found.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Confirm deletion
        int confirmation = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to delete the group \"" + group.getGroupName() + "\"?",
            "Confirm Deletion",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );

        if (confirmation == JOptionPane.YES_OPTION) {
            // Delete the group
            group.deleteFromDatabase();
            JOptionPane.showMessageDialog(this, "Group deleted successfully.", "Deletion Successful", JOptionPane.INFORMATION_MESSAGE);
            window.dispose();
        }
       
    }//GEN-LAST:event_deletegroupActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DemoteMember;
    private javax.swing.JButton ManagePosts;
    private javax.swing.JButton PromoteMember;
    private javax.swing.JButton RemoveMember;
    private javax.swing.JButton changecoverphoto;
    private javax.swing.JButton changedescription;
    private javax.swing.JButton changename;
    private javax.swing.JButton deletegroup;
    // End of variables declaration//GEN-END:variables

    void setDefaultCloseOperation(int DISPOSE_ON_CLOSE) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
