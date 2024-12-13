/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package FrontEnd;

import connecthub.*;
import java.util.*;
import javax.swing.JOptionPane;

/**
 *
 * @author waelj
 */
public class AddFriend extends javax.swing.JFrame {

    /**
     * Creates new form AddFriend
     */
    private User u;
    private UsersDatabase database = new UsersDatabase();
    List<User> users = database.loadUsers();

    public AddFriend(User u) {
        initComponents();
        this.u = u;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        username = new javax.swing.JTextField();
        sendRequest = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Enter user name :");

        sendRequest.setText("Send Request");
        sendRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendRequestActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(86, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(sendRequest)
                .addGap(14, 14, 14))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(sendRequest)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sendRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendRequestActionPerformed

        String userName = username.getText();
        boolean flag1 = true, flag2 = true, flag3 = true;
        if ("".equals(userName) || userName == null) {
            JOptionPane.showMessageDialog(null, "field is empty", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            List<User> friends = u.getFriends();
            List<User> blocked = u.getBlockedUsers();
            for (User k : friends) {
                if (k.getUsername().equals(userName)) {
                    JOptionPane.showMessageDialog(null, userName + " is already your friend", "Error", JOptionPane.ERROR_MESSAGE);
                    flag1 = false;
                    break;
                }
            }
            for (User k : blocked) {
                if (k.getUsername().equals(userName)) {
                    JOptionPane.showMessageDialog(null, userName + " is Blocked", "Error", JOptionPane.ERROR_MESSAGE);
                    flag2 = false;
                    break;
                }
            }
            for (User k : users) {
                if ((k.getUsername().equals(userName)) && flag1 && flag2 && k != u) {
                    u.sendFriendRequest(k);
                    JOptionPane.showMessageDialog(null, "Friend request sent", "Alert", JOptionPane.INFORMATION_MESSAGE);
                    flag3 = false;
                    break;
                }
            }
            if (flag3) {
                JOptionPane.showMessageDialog(null, userName + " is not found", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_sendRequestActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton sendRequest;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
