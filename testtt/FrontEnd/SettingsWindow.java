/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package FrontEnd;

import connecthub.*;
import java.io.File;
import javax.swing.*;

/**
 *
 * @author ADMIN
 */
public class SettingsWindow extends javax.swing.JFrame {
    private Profile window;
    /**
     * Creates new form SettingsWindow
     */
    public SettingsWindow(Profile window) {
        this.window=window;
        initComponents();
    }
    private void saveChanges() {
     
        window.reloadProfileDetails(); 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        UpdateProfilePhoto = new javax.swing.JButton();
        UpdateCoverPhoto = new javax.swing.JButton();
        UpdatePassword = new javax.swing.JButton();
        UpdateUsername = new javax.swing.JButton();
        UpdateBio = new javax.swing.JButton();
        Blocked = new javax.swing.JButton();
        LogOut = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 102, 102));
        setForeground(new java.awt.Color(204, 102, 0));

        UpdateProfilePhoto.setBackground(new java.awt.Color(0, 102, 153));
        UpdateProfilePhoto.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        UpdateProfilePhoto.setForeground(new java.awt.Color(255, 255, 255));
        UpdateProfilePhoto.setText("Change Profile Photo");
        UpdateProfilePhoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateProfilePhotoActionPerformed(evt);
            }
        });

        UpdateCoverPhoto.setBackground(new java.awt.Color(0, 102, 153));
        UpdateCoverPhoto.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        UpdateCoverPhoto.setForeground(new java.awt.Color(255, 255, 255));
        UpdateCoverPhoto.setText("Change Cover Photo");
        UpdateCoverPhoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateCoverPhotoActionPerformed(evt);
            }
        });

        UpdatePassword.setBackground(new java.awt.Color(0, 102, 153));
        UpdatePassword.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        UpdatePassword.setForeground(new java.awt.Color(255, 255, 255));
        UpdatePassword.setText("Change Password");
        UpdatePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdatePasswordActionPerformed(evt);
            }
        });

        UpdateUsername.setBackground(new java.awt.Color(0, 102, 153));
        UpdateUsername.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        UpdateUsername.setForeground(new java.awt.Color(255, 255, 255));
        UpdateUsername.setText("Change Username");
        UpdateUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateUsernameActionPerformed(evt);
            }
        });

        UpdateBio.setBackground(new java.awt.Color(0, 102, 153));
        UpdateBio.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        UpdateBio.setForeground(new java.awt.Color(255, 255, 255));
        UpdateBio.setText("Change Bio");
        UpdateBio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateBioActionPerformed(evt);
            }
        });

        Blocked.setBackground(new java.awt.Color(0, 102, 153));
        Blocked.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Blocked.setForeground(new java.awt.Color(255, 255, 255));
        Blocked.setText("Blocked");
        Blocked.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BlockedActionPerformed(evt);
            }
        });

        LogOut.setBackground(new java.awt.Color(0, 102, 153));
        LogOut.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LogOut.setForeground(new java.awt.Color(255, 255, 255));
        LogOut.setText("LogOut");
        LogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogOutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(UpdateProfilePhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(UpdateCoverPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(UpdatePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(UpdateUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(UpdateBio, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Blocked, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                            .addComponent(LogOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(82, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(UpdateProfilePhoto)
                .addGap(36, 36, 36)
                .addComponent(UpdateCoverPhoto)
                .addGap(32, 32, 32)
                .addComponent(UpdatePassword)
                .addGap(31, 31, 31)
                .addComponent(UpdateUsername)
                .addGap(27, 27, 27)
                .addComponent(UpdateBio)
                .addGap(28, 28, 28)
                .addComponent(Blocked)
                .addGap(29, 29, 29)
                .addComponent(LogOut)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void UpdateCoverPhotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateCoverPhotoActionPerformed
        // Open file chooser to select new cover photo
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a new Cover Photo");
        
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String newCoverPhotoPath = selectedFile.getAbsolutePath();
            
            // Update the user's cover photo
            window.user.changeCoverPhoto(newCoverPhotoPath); //me7taga a7ot hena saveto data base!!!!
            saveChanges();
            
        }
    }//GEN-LAST:event_UpdateCoverPhotoActionPerformed

    private void UpdatePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdatePasswordActionPerformed
        String newPassword = JOptionPane.showInputDialog(this, "Enter new password:");
         if (newPassword != null && !newPassword.trim().isEmpty()) {
             // Password validations
             if (newPassword.length() < 8) {
                 JOptionPane.showMessageDialog(this, "Password must be at least 8 characters long.");
                 return;
             }
             if (!newPassword.matches(".*[A-Z].*")) {
                 JOptionPane.showMessageDialog(this, "Password must contain at least one uppercase letter.");
                 return;
             }
             if (!newPassword.matches(".*[a-z].*")) {
                 JOptionPane.showMessageDialog(this, "Password must contain at least one lowercase letter.");
                 return;
             }
             if (!newPassword.matches(".*\\d.*")) {
                 JOptionPane.showMessageDialog(this, "Password must contain at least one number.");
                 return;
             }
             if (newPassword.matches(".*(password|12345|admin|qwerty).*")) {
                 JOptionPane.showMessageDialog(this, "Password is too weak. Avoid common words or sequences.");
                 return;
             }

             // Confirm password
             String confirmPassword = JOptionPane.showInputDialog(this, "Confirm new password:");
             if (!newPassword.equals(confirmPassword)) {
                 JOptionPane.showMessageDialog(this, "Passwords do not match. Try again.");
                 return;
        }
        
        // Update password
        window.user.setPassword(newPassword);
            JOptionPane.showMessageDialog(this, "Password updated successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Password cannot be empty.");
        }
        saveChanges();
    }//GEN-LAST:event_UpdatePasswordActionPerformed

    private void UpdateProfilePhotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateProfilePhotoActionPerformed
        // Open file chooser to select new cover photo
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a new Profile Photo");
        
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String newProfilePhotoPath = selectedFile.getAbsolutePath();
            
            // Update the user's cover photo
            window.user.changeProfilePhoto(newProfilePhotoPath); //me7taga a7ot hena saveto data base!!!!
        }
        saveChanges();
    }//GEN-LAST:event_UpdateProfilePhotoActionPerformed

    private void UpdateUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateUsernameActionPerformed
        String newUsername = JOptionPane.showInputDialog(this, "Enter new username:");
        if (newUsername != null && !newUsername.trim().isEmpty()) {
            window.user.setUsername(newUsername);
            JOptionPane.showMessageDialog(this, "Username updated successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Username cannot be empty.");
        }
        saveChanges();
    }//GEN-LAST:event_UpdateUsernameActionPerformed

    private void UpdateBioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateBioActionPerformed
        String newBio = JOptionPane.showInputDialog(this, "Enter new bio:");
        if (newBio != null && !newBio.trim().isEmpty()) {
            window.user.changeBio(newBio);
            JOptionPane.showMessageDialog(this, "Bio updated successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Bio cannot be empty.");
        }
        saveChanges();
    }//GEN-LAST:event_UpdateBioActionPerformed

    private void LogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogOutActionPerformed
        WelcomePage welcome=new WelcomePage();
        welcome.setVisible(true);
        this.dispose();
        window.dispose();
    }//GEN-LAST:event_LogOutActionPerformed

    private void BlockedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BlockedActionPerformed
       Blocked b= new Blocked(window.user);
       b.setVisible(true);
       b.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_BlockedActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(SettingsWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(SettingsWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(SettingsWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(SettingsWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                Profile Profile=null; 
//                new SettingsWindow(Profile).setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Blocked;
    private javax.swing.JButton LogOut;
    private javax.swing.JButton UpdateBio;
    private javax.swing.JButton UpdateCoverPhoto;
    private javax.swing.JButton UpdatePassword;
    private javax.swing.JButton UpdateProfilePhoto;
    private javax.swing.JButton UpdateUsername;
    // End of variables declaration//GEN-END:variables
}
