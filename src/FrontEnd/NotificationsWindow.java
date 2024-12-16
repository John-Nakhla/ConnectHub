
package FrontEnd;

import connecthub.*;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.*;


public class NotificationsWindow extends javax.swing.JFrame {


    private User user;

    public NotificationsWindow(User user) {
        initComponents();
        this.user = user;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        notificationsPanel.setLayout(new BoxLayout(notificationsPanel, BoxLayout.Y_AXIS));
        refresh();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        notificationsPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        refresh = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout notificationsPanelLayout = new javax.swing.GroupLayout(notificationsPanel);
        notificationsPanel.setLayout(notificationsPanelLayout);
        notificationsPanelLayout.setHorizontalGroup(
            notificationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 385, Short.MAX_VALUE)
        );
        notificationsPanelLayout.setVerticalGroup(
            notificationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 390, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(notificationsPanel);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Notifications");

        refresh.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        refresh.setText("Refresh");
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(refresh)
                .addGap(45, 45, 45))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(refresh, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                        .addGap(5, 5, 5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
        refresh();
    }//GEN-LAST:event_refreshActionPerformed

    private void refresh() {
        notificationsPanel.removeAll();
        NotificationsDatabase db = new NotificationsDatabase();
        List<BasicNotification> notifications = db.getAllNotifications();
        for (BasicNotification n : notifications) {
            if (n.getOwner().equals(user.getUserId()) && "active".equals(n.getStatus())) {
                
                if ("FR".equals(n.getType())) {
                    
                    JPanel notificationItem = new JPanel(new FlowLayout(FlowLayout.LEFT));
                    JLabel notificationLabel = new JLabel(n.getMessage());
                    notificationItem.add(notificationLabel);
 
                    if (!n.getMessage().contains("accepted") && !n.getMessage().contains("declined")){
                        
                        List<FriendRequest> requests = user.getFriendRequests();
                        JButton acceptBtn = new JButton("Accept");
                        acceptBtn.addActionListener((ActionEvent e) -> {
                            for (FriendRequest fr : requests) {
                                if (fr.getSender().getUserId().equals(n.getSender()) && fr.getReceiver().getUserId().equals(n.getOwner())) {
                                    user.acceptFriendRequest(fr);
                                    JOptionPane.showMessageDialog(this, "Accepted");
                                    notificationsPanel.remove(notificationItem);
                                    refresh();
                                }
                            }
                            n.setStatus("Inactive");
                            n.saveToFile();
                        });

                        notificationItem.add(acceptBtn);       

                        JButton declineBtn = new JButton("Decline");
                        declineBtn.addActionListener((ActionEvent e) -> {
                            for (FriendRequest fr : requests) {
                                if (fr.getSender().getUserId().equals(n.getSender()) && fr.getReceiver().getUserId().equals(n.getOwner())) {
                                    user.declineFriendRequest(fr);
                                    JOptionPane.showMessageDialog(this, "Accepted");
                                    notificationsPanel.remove(notificationItem);
                                }
                            }
                            n.setStatus("Inactive");
                            n.saveToFile();
                        });
                        notificationItem.add(declineBtn);
                    }
                    
                    notificationsPanel.add(notificationItem);
                }
                
                else if ("Gr".equals(n.getType())) {
                    
                    JPanel notificationItem = new JPanel(new FlowLayout(FlowLayout.LEFT));
                    JLabel notificationLabel = new JLabel(n.getMessage());
                    notificationItem.add(notificationLabel);

                    JButton viewBtn = new JButton("View Group");
                    viewBtn.addActionListener((ActionEvent e) -> {
                        GroupsDatabase gdb = new GroupsDatabase();
                        GroupWindow window = new GroupWindow(user, gdb.searchGroup(n.getSender()));
                        window.setVisible(true);
                        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        notificationsPanel.remove(notificationItem);

                    });

                    notificationItem.add(viewBtn);       
                    notificationsPanel.add(notificationItem);
                }
    
                notificationsPanel.revalidate();
                notificationsPanel.repaint();
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel notificationsPanel;
    private javax.swing.JButton refresh;
    // End of variables declaration//GEN-END:variables
}
