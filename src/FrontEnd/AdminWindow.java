
package FrontEnd;

import connecthub.Group;
import connecthub.GroupAdmin;
import connecthub.GroupCreator;
import connecthub.GroupMember;
import connecthub.User;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;


public class AdminWindow extends javax.swing.JPanel {
    private GroupWindow window;
    Group group;
    GroupAdmin admin;
    public AdminWindow(GroupWindow window ,User user, Group group) {
        initComponents();
        this.group = group;
        this.admin = new GroupAdmin(user.getUsername(), group.getGroupName());
        this.window=window;
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        viewrequests = new javax.swing.JButton();
        removemembers = new javax.swing.JButton();
        manageposts = new javax.swing.JButton();

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(viewrequests, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(removemembers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(manageposts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(viewrequests)
                .addGap(18, 18, 18)
                .addComponent(removemembers)
                .addGap(26, 26, 26)
                .addComponent(manageposts)
                .addContainerGap(28, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

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
        // TODO add your handling code here:
    }//GEN-LAST:event_managepostsActionPerformed

    private void viewrequestsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewrequestsActionPerformed
        GroupRequest g = new GroupRequest(admin,group);
        g.setVisible(true);
        g.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_viewrequestsActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton manageposts;
    private javax.swing.JButton removemembers;
    private javax.swing.JButton viewrequests;
    // End of variables declaration//GEN-END:variables

    void setDefaultCloseOperation(int DISPOSE_ON_CLOSE) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


}
