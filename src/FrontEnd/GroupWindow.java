
package FrontEnd;


import connecthub.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;


public class GroupWindow extends javax.swing.JFrame {
    
    User user;
    Group group;
    public GroupWindow(User user, Group group) {
        initComponents();
        this.user=user;
        this.group = group;
        
        loadCoverPhoto();
        loadGroupDetails();
        loadGroupPosts();
        loadGroupMembers();
    }
    private void loadCoverPhoto() {
        try {
            Image coverPhoto = new ImageIcon(group.getGroupPhoto()).getImage();
            Image scaledCoverPhoto = coverPhoto.getScaledInstance(CoverPhotoPanel.getWidth(), CoverPhotoPanel.getHeight(), Image.SCALE_SMOOTH);

            JPanel coverPhotoPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(scaledCoverPhoto, 0, 0, getWidth(), getHeight(), this);
                }
            };
            coverPhotoPanel.setBounds(0, 0, CoverPhotoPanel.getWidth(), CoverPhotoPanel.getHeight());
            CoverPhotoPanel.add(coverPhotoPanel);
            CoverPhotoPanel.repaint();
            

            // Ensure the panel updates
        } catch (Exception e) {
            JLabel errorLabel = new JLabel("Cover Photo Not Available");
            errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
            CoverPhotoPanel.add(errorLabel);
            CoverPhotoPanel.repaint();
        }
    }
    
    private void loadGroupDetails() {
        grpname.setText(group.getGroupName());
        grpdesc.setText(group.getDescription());
    }
    
    private void loadGroupPosts() {

        JPanel postsPanel = new JPanel();
        postsPanel.setLayout(new BoxLayout(postsPanel, BoxLayout.Y_AXIS)); // Set vertical layout

        GroupsDatabase db = new GroupsDatabase();
        db.searchGroup(group.getGroupName());

        PostsDatabase posts = new PostsDatabase();
        
        java.util.List<Posts> groupPosts = posts.getGroupPosts(group.getGroupName());
        
        for (Posts content : groupPosts) {

            String contentText = content.getContent();
            String contentImgDir = content.getImg();

            if ((contentText != null && !contentText.isEmpty()) || (contentImgDir != null && !contentImgDir.isEmpty())) {
                // Create a custom Post component for each post
                Post post = new Post(contentText, contentImgDir);
                post.setMaximumSize(new Dimension(550, post.getPreferredSize().height)); // Set a maximum width for posts
                postsPanel.add(post); // Add the post to the posts panel
            }
        }

        // Refresh the posts panel to ensure the new posts are displayed
        postsPanel.revalidate();
        postsPanel.repaint();

        // Set the posts panel to the scroll pane
        PostsPanelScroll.setViewportView(postsPanel);

    }
    
    private void loadGroupMembers() {
        JPanel memberspanel = new JPanel();
        memberspanel.setLayout(new BoxLayout(memberspanel, BoxLayout.Y_AXIS));
        
        for (String member : group.GetGroupPeople()) {
            JLabel MemberLabel = new JLabel(member); //Adds Member and his role
            MemberLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            memberspanel.add(MemberLabel);
        }
        memberspanel.revalidate();
        memberspanel.repaint();
        MembersPanelScroll.setViewportView(memberspanel);
    }
    
    
    public void reloadGroupDetails() {
        loadCoverPhoto();
        loadGroupDetails();
        loadGroupMembers();

    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        CoverPhotoPanel = new javax.swing.JPanel();
        grpname = new javax.swing.JLabel();
        grpdesc = new javax.swing.JLabel();
        options = new javax.swing.JButton();
        PostsPanelScroll = new javax.swing.JScrollPane();
        postspanel = new javax.swing.JPanel();
        MembersPanelScroll = new javax.swing.JScrollPane();
        memberspanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        CoverPhotoPanel.setBackground(new java.awt.Color(204, 204, 255));
        CoverPhotoPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        javax.swing.GroupLayout CoverPhotoPanelLayout = new javax.swing.GroupLayout(CoverPhotoPanel);
        CoverPhotoPanel.setLayout(CoverPhotoPanelLayout);
        CoverPhotoPanelLayout.setHorizontalGroup(
            CoverPhotoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        CoverPhotoPanelLayout.setVerticalGroup(
            CoverPhotoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 148, Short.MAX_VALUE)
        );

        grpname.setBackground(new java.awt.Color(255, 255, 255));
        grpname.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        grpname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        grpname.setText("Group Name");

        grpdesc.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        grpdesc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        grpdesc.setText("Group Description");

        options.setBackground(new java.awt.Color(0, 102, 153));
        options.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        options.setForeground(new java.awt.Color(255, 255, 255));
        options.setText("Options");
        options.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optionsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout postspanelLayout = new javax.swing.GroupLayout(postspanel);
        postspanel.setLayout(postspanelLayout);
        postspanelLayout.setHorizontalGroup(
            postspanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 446, Short.MAX_VALUE)
        );
        postspanelLayout.setVerticalGroup(
            postspanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 399, Short.MAX_VALUE)
        );

        PostsPanelScroll.setViewportView(postspanel);

        javax.swing.GroupLayout memberspanelLayout = new javax.swing.GroupLayout(memberspanel);
        memberspanel.setLayout(memberspanelLayout);
        memberspanelLayout.setHorizontalGroup(
            memberspanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 158, Short.MAX_VALUE)
        );
        memberspanelLayout.setVerticalGroup(
            memberspanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 359, Short.MAX_VALUE)
        );

        MembersPanelScroll.setViewportView(memberspanel);

        jLayeredPane1.setLayer(CoverPhotoPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(grpname, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(grpdesc, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(options, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(PostsPanelScroll, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(MembersPanelScroll, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(CoverPhotoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addComponent(grpname, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(grpdesc, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(PostsPanelScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(options))
                            .addComponent(MembersPanelScroll))
                        .addGap(16, 16, 16))))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CoverPhotoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(grpname, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(grpdesc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PostsPanelScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(options)
                        .addGap(57, 57, 57)
                        .addComponent(MembersPanelScroll)))
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void optionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optionsActionPerformed
        if(user.getUsername().equals(group.getCreatorUsername())){
            PrimaryAdminWindow priadmin=new PrimaryAdminWindow(this,user, group);
            priadmin.setVisible(true);
            priadmin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }  
        else if(group.isAdmin(user.getUsername())){
            AdminWindow adminwindow=new AdminWindow(user, group);
            adminwindow.setVisible(true);
            adminwindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        else if(group.isMember(user.getUsername())){
            String[] options = {"Create Post", "Leave Group"};
              int choice = JOptionPane.showOptionDialog(this, 
                                                         "What would you like to do?", 
                                                         "Options", 
                                                         JOptionPane.DEFAULT_OPTION, 
                                                         JOptionPane.INFORMATION_MESSAGE, 
                                                         null, options, options[0]);

              // Handle the option selected by the user
            if (choice == 0) {
                // Open CreatePostWindow if "Create Post" is selected
                CreateContentWindow createPostWindow = new CreateContentWindow(this,true,"p"); // Pass the parent GroupWindow instance
                createPostWindow.pack();
                createPostWindow.setVisible(true);
                createPostWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            } else if (choice == 1) {
                // Handle the "Leave Group" option
                int confirmLeave = JOptionPane.showConfirmDialog(this, 
                    "Are you sure you want to leave the group?", "Leave Group", 
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                if (confirmLeave == JOptionPane.YES_OPTION) {
                    group.removeMember(user.getUsername()); //The memebr leaves
                }
            }
        }
        else if(group.isRequested(user.getUsername())){
            JOptionPane.showMessageDialog(this, "You already sent a join request, Please wait.", "Request already Sent", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            int choice = JOptionPane.showConfirmDialog(
               this,
               "You are not part of this group. Would you like to send a join request?",
               "Join Group",
               JOptionPane.YES_NO_OPTION,
               JOptionPane.QUESTION_MESSAGE
           );

           if (choice == JOptionPane.YES_OPTION) {
               group.sendJoinRequest(user.getUsername());
               JOptionPane.showMessageDialog(this, "Your join request has been sent.", "Request Sent", JOptionPane.INFORMATION_MESSAGE);
           } 
        }
    }//GEN-LAST:event_optionsActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CoverPhotoPanel;
    private javax.swing.JScrollPane MembersPanelScroll;
    private javax.swing.JScrollPane PostsPanelScroll;
    private javax.swing.JLabel grpdesc;
    private javax.swing.JLabel grpname;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel memberspanel;
    private javax.swing.JButton options;
    private javax.swing.JPanel postspanel;
    // End of variables declaration//GEN-END:variables
}
