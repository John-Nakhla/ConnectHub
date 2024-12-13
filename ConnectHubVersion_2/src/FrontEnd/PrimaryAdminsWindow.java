/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package FrontEnd;

import connecthub.*;
import connecthub.GroupCreator;
import connecthub.User;
import java.io.File;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author ADMIN
 */
public class PrimaryAdminsWindow extends javax.swing.JFrame {

    /**
     * Creates new form GroupsWindow
     */
    private GroupWindow window;
     private GroupCreator creator;
     private Group group;
    
    public PrimaryAdminsWindow(GroupWindow window,User user, Group group) {
        initComponents();
        this.window=window;
        this.creator =new GroupCreator(group.getCreatorUsername(),group.getGroupName());
        this.group = group;
    }

    private PrimaryAdminsWindow() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        RemoveMember = new javax.swing.JButton();
        ManagePosts = new javax.swing.JButton();
        deletegroup = new javax.swing.JButton();
        changecoverphoto = new javax.swing.JButton();
        changename = new javax.swing.JButton();
        changedescription = new javax.swing.JButton();
        PromoteMember = new javax.swing.JButton();
        DemoteMember = new javax.swing.JButton();
        viewrequests = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        viewrequests.setBackground(new java.awt.Color(153, 102, 0));
        viewrequests.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        viewrequests.setForeground(new java.awt.Color(255, 255, 255));
        viewrequests.setText("View Group Requests");
        viewrequests.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewrequestsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
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
                    .addComponent(changedescription, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(viewrequests, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(69, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(viewrequests)
                .addGap(18, 18, 18)
                .addComponent(deletegroup)
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        ReviewPendingPosts p=new ReviewPendingPosts(creator,group,window);
        p.setVisible(true);
        p.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_ManagePostsActionPerformed

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

    private void changecoverphotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changecoverphotoActionPerformed
        // Open file chooser to select new cover photo
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a new Cover Photo");

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String newCoverPhotoPath = selectedFile.getAbsolutePath();

            // Update the Group's cover photo
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

        JScrollPane scrollPane = new JScrollPane(memberList);

        // Show dialog to select a member
        int result = JOptionPane.showConfirmDialog(
            this,
            scrollPane,
            "Select a member to promote to admin:",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            int selectedIndex = memberList.getSelectedIndex();
            if (selectedIndex != -1) {
                GroupMember selectedMember = nonAdminMembers.get(selectedIndex);

                // Promote the selected member
                if (group.isMember(selectedMember.getUsername())) {
                    group.removeMember(selectedMember.getUsername());
                    group.addAdmin(new GroupAdmin(selectedMember.getUsername(), selectedMember.getGroupname()));
                    group.saveToFile();
                    
                    JOptionPane.showMessageDialog(this, "Member promoted to admin successfully.", "Promotion Successful", JOptionPane.INFORMATION_MESSAGE);
                    window.reloadGroupDetails(); // Refresh the UI
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to promote member. Please try again.", "Promotion Failed", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "No member selected.", "Invalid Selection", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_PromoteMemberActionPerformed

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
                if (group.isAdmin(selectedAdmin.getUsername())) {
                    group.removeAdmin(selectedAdmin.getUsername());
                    group.addMember(new GroupMember(selectedAdmin.getUsername(), selectedAdmin.getGroupname()));
                    group.saveToFile();
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

    private void viewrequestsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewrequestsActionPerformed
        GroupsRequest g = new GroupsRequest(window,creator,group);
        g.setVisible(true);
        g.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
    }//GEN-LAST:event_viewrequestsActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PrimaryAdminsWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrimaryAdminsWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrimaryAdminsWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrimaryAdminsWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PrimaryAdminsWindow().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DemoteMember;
    private javax.swing.JButton ManagePosts;
    private javax.swing.JButton PromoteMember;
    private javax.swing.JButton RemoveMember;
    private javax.swing.JButton changecoverphoto;
    private javax.swing.JButton changedescription;
    private javax.swing.JButton changename;
    private javax.swing.JButton deletegroup;
    private javax.swing.JButton viewrequests;
    // End of variables declaration//GEN-END:variables
}
