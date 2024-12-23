/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package FrontEnd;

import connecthub.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.*;

/**
 *
 * @author waelj
 */
public class Search extends javax.swing.JFrame {

    /**
     * Creates new form Search
     */
    private User u;
    private UsersDatabase database = new UsersDatabase();

    public Search(User u) {
        initComponents();
        u.update();
        this.u = u;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    }

    private Search() {
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

        top = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        username = new javax.swing.JTextField();
        search = new javax.swing.JButton();
        scrollable = new javax.swing.JScrollPane();
        mainPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Enter User name");

        search.setText("Search");
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout topLayout = new javax.swing.GroupLayout(top);
        top.setLayout(topLayout);
        topLayout.setHorizontalGroup(
            topLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(search)
                .addGap(40, 40, 40))
        );
        topLayout.setVerticalGroup(
            topLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topLayout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(topLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13))
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 423, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 345, Short.MAX_VALUE)
        );

        scrollable.setViewportView(mainPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(top, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(scrollable)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(top, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollable))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        mainPanel.removeAll();
        String name = username.getText();
        if (name.equals("")) {
            JOptionPane.showMessageDialog(this, "Input is empty", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            boolean flag = false;
            List<User> users = AccountManagement.getAdmin().getUsers();
            for (User k : users) {
                if (k.getUsername().equals(name)) {
                    flag = true;
                    JPanel userItem = new JPanel(new FlowLayout(FlowLayout.LEFT));
                    JLabel nameLabel = new JLabel(k.getUsername());
                    if (u.isFriend(k)) {
                        userItem.add(nameLabel);
                        JButton removeButton = new JButton("Remove");
                        JButton profileButton = new JButton("Profile");
                        JButton blockButton = new JButton("Block");
                        removeButton.addActionListener(e -> removeFriend(k));
                        profileButton.addActionListener(e -> viewProfile(k));
                        blockButton.addActionListener(e -> blockUser(k));
                        userItem.add(removeButton);
                        userItem.add(profileButton);
                        userItem.add(blockButton);
                    } else if (!u.isBlocked(k) && !(k.getUserId().equals(u.getUserId()))) {
                        userItem.add(nameLabel);
                        flag = true;
                        JButton addButton = new JButton("Add");
                        JButton profileButton = new JButton("Profile");
                        JButton blockButton = new JButton("Block");
                        addButton.addActionListener(e -> addFriend(k));
                        profileButton.addActionListener(e -> viewProfile(k));
                        blockButton.addActionListener(e -> blockUser(k));
                        userItem.add(addButton);
                        userItem.add(profileButton);
                        userItem.add(blockButton);
                    } else if (k.getUserId().equals(u.getUserId())) {
                        userItem.add(nameLabel);
                        flag = true;
                        JButton profileButton = new JButton("Profile");
                        profileButton.addActionListener(e -> viewMyProfile(k));
                        userItem.add(profileButton);
                    }
                    mainPanel.add(userItem);
                }
            }
            if (!flag) {
                JOptionPane.showMessageDialog(this, "User not found", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        mainPanel.getParent().revalidate();
        mainPanel.getParent().repaint();
    }//GEN-LAST:event_searchActionPerformed
    private void removeFriend(User user) {
        System.out.println("in remove in search");
        u.removeFriend(user);
        user.removeFriend(u);
    }

    private void addFriend(User user) {
        u.sendFriendRequest(user);
    }

    private void viewProfile(User user) {
        Profile p = new Profile(user,false);
        p.setVisible(true);
    }

    private void blockUser(User user) {
       u.blockUser(user);
    }
    private void viewMyProfile(User user)
    {
        Profile p = new Profile(user,true);
        p.setVisible(true);
    }
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
            java.util.logging.Logger.getLogger(Search.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Search.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Search.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Search.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Search().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JScrollPane scrollable;
    private javax.swing.JButton search;
    private javax.swing.JPanel top;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
