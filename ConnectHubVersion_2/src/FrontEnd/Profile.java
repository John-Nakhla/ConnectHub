package FrontEnd;

import connecthub.Content;
import connecthub.NewsFeed;
import connecthub.UsersDatabase;
import connecthub.User;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import java.util.List;
import org.json.JSONArray;

public class Profile extends JFrame {

    User user;

    public Profile(User user, boolean allowSettings) {
        initComponents();
        this.user = user;
        user.update();
        loadCoverPhoto();
        loadProfilePhoto();
        loadUserDetails();
        loadUserPosts();
        loadUserFriends();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        settingsBtn.setVisible(allowSettings);
    }

    private void loadCoverPhoto() {
        try {
            Image coverPhoto = new ImageIcon(user.getCoverPhoto()).getImage();
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
            loadProfilePhoto();

            // Ensure the panel updates
        } catch (Exception e) {
            JLabel errorLabel = new JLabel("Cover Photo Not Available");
            errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
            CoverPhotoPanel.add(errorLabel);
            CoverPhotoPanel.repaint();
        }
    }

    private void loadProfilePhoto() {
        try {
            Image profilePhoto = new ImageIcon(user.getProfilePhoto()).getImage();
            Image scaledProfilePhoto = profilePhoto.getScaledInstance(ProfilePhotoPanel.getWidth(), ProfilePhotoPanel.getHeight(), Image.SCALE_SMOOTH);

            JPanel profilePhotoPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(scaledProfilePhoto, 0, 0, getWidth(), getHeight(), this);
                }
            };
            profilePhotoPanel.setBounds(0, 0, ProfilePhotoPanel.getWidth(), ProfilePhotoPanel.getHeight());
            ProfilePhotoPanel.add(profilePhotoPanel);
            ProfilePhotoPanel.repaint();
            loadUserDetails();
        } catch (Exception e) {
            JLabel errorLabel = new JLabel("Profile Photo Not Available");
            errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
            ProfilePhotoPanel.add(errorLabel);
            ProfilePhotoPanel.repaint();
        }
    }

    private void loadUserDetails() {
        jLabel1.setText(user.getUsername());
        jLabel2.setText(user.getBio());
    }

    private void loadUserPosts() {
        postsPanel.setLayout(new BoxLayout(postsPanel, BoxLayout.Y_AXIS)); // Set vertical layout

        UsersDatabase db = new UsersDatabase();
        db.loadUsers();

        NewsFeed myContent = new NewsFeed(db);

        for (Content content : myContent.getContentById(user.getUserId())) {

            if (!content.isStory()) {
                String contentText = content.getContent();
                String contentImgDir = content.getImg();

                if ((contentText != null && !contentText.isEmpty()) || (contentImgDir != null && !contentImgDir.isEmpty())) {
                    // Create a custom Post component for each post
                    Post post = new Post(contentText, contentImgDir);
                    post.setMaximumSize(new Dimension(550, post.getPreferredSize().height)); // Set a maximum width for posts
                    postsPanel.add(post); // Add the post to the posts panel
                }
            }
        }

        // Refresh the posts panel to ensure the new posts are displayed
        postsPanel.revalidate();
        postsPanel.repaint();

        // Set the posts panel to the scroll pane
        PostsPanelScroll.setViewportView(postsPanel);

    }

    private void loadUserFriends() {
        JPanel friendsPanel = new JPanel();
        friendsPanel.setLayout(new BoxLayout(friendsPanel, BoxLayout.Y_AXIS));

        for (User friend : user.getFriends()) {
            JLabel friendLabel = new JLabel(friend.getUsername() + " (" + (friend.isStatus() ? "Online" : "Offline") + ")");
            friendLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            friendsPanel.add(friendLabel);
        }
        friendsPanel.revalidate();
        friendsPanel.repaint();
        FriendsPanelScroll.setViewportView(friendsPanel);
    }

    public void reloadProfileDetails() {
        loadCoverPhoto();
        loadProfilePhoto();
        loadUserDetails();
        loadUserFriends();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        PostsPanelScroll = new javax.swing.JScrollPane();
        postsPanel = new javax.swing.JPanel();
        FriendsPanelScroll = new javax.swing.JScrollPane();
        FriendsPanel = new javax.swing.JPanel();
        CoverPhotoPanel = new javax.swing.JPanel();
        ProfilePhotoPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        settingsBtn = new javax.swing.JButton();
        retrunBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Profile Page");

        jLayeredPane1.setBackground(new java.awt.Color(153, 0, 255));

        javax.swing.GroupLayout postsPanelLayout = new javax.swing.GroupLayout(postsPanel);
        postsPanel.setLayout(postsPanelLayout);
        postsPanelLayout.setHorizontalGroup(
            postsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 472, Short.MAX_VALUE)
        );
        postsPanelLayout.setVerticalGroup(
            postsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 382, Short.MAX_VALUE)
        );

        PostsPanelScroll.setViewportView(postsPanel);

        FriendsPanelScroll.setBackground(new java.awt.Color(102, 102, 102));

        FriendsPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout FriendsPanelLayout = new javax.swing.GroupLayout(FriendsPanel);
        FriendsPanel.setLayout(FriendsPanelLayout);
        FriendsPanelLayout.setHorizontalGroup(
            FriendsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 188, Short.MAX_VALUE)
        );
        FriendsPanelLayout.setVerticalGroup(
            FriendsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 382, Short.MAX_VALUE)
        );

        FriendsPanelScroll.setViewportView(FriendsPanel);

        CoverPhotoPanel.setBackground(new java.awt.Color(255, 255, 102));
        CoverPhotoPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));

        javax.swing.GroupLayout CoverPhotoPanelLayout = new javax.swing.GroupLayout(CoverPhotoPanel);
        CoverPhotoPanel.setLayout(CoverPhotoPanelLayout);
        CoverPhotoPanelLayout.setHorizontalGroup(
            CoverPhotoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        CoverPhotoPanelLayout.setVerticalGroup(
            CoverPhotoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );

        ProfilePhotoPanel.setBackground(new java.awt.Color(255, 0, 0));
        ProfilePhotoPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));

        javax.swing.GroupLayout ProfilePhotoPanelLayout = new javax.swing.GroupLayout(ProfilePhotoPanel);
        ProfilePhotoPanel.setLayout(ProfilePhotoPanelLayout);
        ProfilePhotoPanelLayout.setHorizontalGroup(
            ProfilePhotoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 77, Short.MAX_VALUE)
        );
        ProfilePhotoPanelLayout.setVerticalGroup(
            ProfilePhotoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 77, Short.MAX_VALUE)
        );

        jLabel1.setBackground(new java.awt.Color(255, 204, 204));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Name");

        jLabel2.setBackground(new java.awt.Color(255, 204, 204));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Bio");

        settingsBtn.setBackground(new java.awt.Color(0, 102, 102));
        settingsBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        settingsBtn.setForeground(new java.awt.Color(255, 255, 255));
        settingsBtn.setText("Settings");
        settingsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsBtnActionPerformed(evt);
            }
        });

        retrunBtn.setBackground(new java.awt.Color(0, 102, 102));
        retrunBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        retrunBtn.setForeground(new java.awt.Color(255, 255, 255));
        retrunBtn.setText("Return");
        retrunBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                retrunBtnActionPerformed(evt);
            }
        });

        jLayeredPane1.setLayer(PostsPanelScroll, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(FriendsPanelScroll, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(CoverPhotoPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(ProfilePhotoPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(settingsBtn, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(retrunBtn, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addComponent(PostsPanelScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(FriendsPanelScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 18, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(CoverPhotoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(ProfilePhotoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(retrunBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(settingsBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                .addComponent(CoverPhotoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(ProfilePhotoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(settingsBtn)
                        .addGap(34, 34, 34)
                        .addComponent(retrunBtn))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(FriendsPanelScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 234, Short.MAX_VALUE))
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(PostsPanelScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void settingsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsBtnActionPerformed
        SettingsWindow settingsWindow = new SettingsWindow(this);
        settingsWindow.setVisible(true);
        settingsWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_settingsBtnActionPerformed

    private void retrunBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_retrunBtnActionPerformed
        this.dispose();
    }//GEN-LAST:event_retrunBtnActionPerformed

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
//            java.util.logging.Logger.getLogger(Profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        UsersDatabase database = new UsersDatabase();
//        database.loadUsers();
//
//        // Step 1: Create a new list of users
//        java.util.List<User> users = new ArrayList<>();
//
//        // Add some users to the list
//        User user1 = new User(
//                "johndoe@example.com",
//                "johndoe",
//                "password123",
//                "1995-05-15",
//                "path/to/photo.jpg",
//                "path/to/cover.jpg",
//                "Hello, I am John Doe!"
//        );
//        user1.setStatus(true);
//
//        User user2 = new User(
//                "janedoe@example.com",
//                "janedoe",
//                "password456",
//                "1998-03-22",
//                "path/to/photo2.jpg",
//                "path/to/cover2.jpg",
//                "Hi, I'm Jane Doe!"
//        );
//        user2.setStatus(false);
//
//        User user3 = new User(
//                "janedoe@example.com",
//                "janedoe",
//                "password456",
//                "1998-03-22",
//                "path/to/photo2.jpg",
//                "path/to/cover2.jpg",
//                "Hi, I'm Jene Doe!"
//        );
//        user3.setStatus(true);
//
//        // Establish friendship between user1 and user2
//        user1.addFriend(user2);
//        user2.addFriend(user1);
//        user1.addFriend(user3);
//        user3.addFriend(user1);
//        user3.addFriend(user2);
//        user2.addFriend(user3);
//        // Add users to the list
//        users.add(user1);
//        users.add(user2);
//        users.add(user3);
//
//        // Step 2: Save the users to a JSON file
//        database.saveUsers(users);
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Profile(user1).setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CoverPhotoPanel;
    private javax.swing.JPanel FriendsPanel;
    private javax.swing.JScrollPane FriendsPanelScroll;
    private javax.swing.JScrollPane PostsPanelScroll;
    private javax.swing.JPanel ProfilePhotoPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel postsPanel;
    private javax.swing.JButton retrunBtn;
    private javax.swing.JButton settingsBtn;
    // End of variables declaration//GEN-END:variables
}
