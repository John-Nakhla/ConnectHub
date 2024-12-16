
package FrontEnd;

import connecthub.*;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.*;
import org.json.JSONArray;


public class GroupSearch extends javax.swing.JFrame {


    private User u;
    private GroupsDatabase database = new GroupsDatabase();
    Notification notification;
    Notification groupNotification = new GroupNotification(notification);

    public GroupSearch(User u) {
        initComponents();
        u.update();
        this.u = u;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    }

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

        jLabel1.setText("Enter Group name");

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
            .addComponent(scrollable, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(top, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollable, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE))
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
            JSONArray JList = database.loadGroups();
            List<Group> groups = database.convertJsonArrayToGroupList(JList);
            for (Group k : groups) {
                if (k.getGroupName().equals(name)) {
                    JPanel groupPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                    flag = true;
                    if (k.isMember(u.getUsername()) || k.isAdmin(u.getUsername()) || k.isCreator(u.getUsername())) {
                        JLabel nameLabel = new JLabel(k.getGroupName());
                        JButton view = new JButton("View");
                        view.addActionListener(e -> viewGroup(k));
                        groupPanel.add(nameLabel);
                        groupPanel.add(view);
                    } else {
                        JLabel nameLabel = new JLabel(k.getGroupName());
                        JButton join = new JButton("Join");
                        JButton view = new JButton("View");
                        join.addActionListener(e -> joinGroup(k));
                        view.addActionListener(e -> viewGroup(k));
                        groupPanel.add(nameLabel);
                        groupPanel.add(join);
                        groupPanel.add(view);
                    }
                    mainPanel.add(groupPanel);
                }
            }
            if (!flag) {
                JOptionPane.showMessageDialog(this, "User not found", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        mainPanel.getParent().revalidate();
        mainPanel.getParent().repaint();
    }//GEN-LAST:event_searchActionPerformed

    private void joinGroup(Group group) {

        group.sendJoinRequest(u.getUsername(), u.getUserId());
        groupNotification.handleAction("moderators", u.getUserId(), u.getUsername() + " wants to join the group", group.getGroupName());
        JOptionPane.showMessageDialog(this, "Your join request has been sent.", "Request Sent", JOptionPane.INFORMATION_MESSAGE);
    }

    private void viewGroup(Group group) {
        GroupWindow window = new GroupWindow(u, group);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
