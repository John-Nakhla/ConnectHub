
package FrontEnd;

import backend.Content;
import backend.NewsFeed;
import backend.UsersDatabase;
import backend.User;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import java.util.List;
import org.json.JSONArray;


public class Profile extends javax.swing.JFrame {
    
    User user;

    public Profile(User user) {
        initComponents();
        this.user=user;

        
        loadCoverPhoto();
        loadProfilePhoto();
        loadUserDetails();
        loadUserPosts();
        loadUserFriends();  
  
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
        JPanel postsPanel = new JPanel();
        postsPanel.setLayout(new BoxLayout(postsPanel, BoxLayout.Y_AXIS));

        UsersDatabase db = new UsersDatabase();
        db.loadUsers();
        
        NewsFeed myContent = new NewsFeed(db);
        
        for (Content content : myContent.getContentById(user.getUserId())) {
//            JLabel postLabel = new JLabel(post);
//            postLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//            postsPanel.add(postLabel);
            String contentText = content.getContent();
            String contentImgDir = content.getImg();

            if (!"".equals(contentText) || !"".equals(contentImgDir)) {
                Post post = new Post(contentText, contentImgDir);
                post.setMaximumSize(new Dimension(550, post.getPreferredSize().height));
                postsPanel.add(post);
                postsPanel.revalidate();
                postsPanel.repaint();
            }
        }

//        PostsPanelScroll.setViewportView(postsPanel);
    }
    
    private void loadUserFriends() {
        JPanel friendsPanel = new JPanel();
        friendsPanel.setLayout(new BoxLayout(friendsPanel, BoxLayout.Y_AXIS));

        for (User friend : user.getFriends()) {
            JLabel friendLabel = new JLabel(friend.getUsername() + " (" + (friend.isStatus()? "Online" : "Offline") + ")");
            friendLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            friendsPanel.add(friendLabel);
        }

        FriendsPanelScroll.setViewportView(FriendsPanel);
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
        PostsPanel = new javax.swing.JPanel();
        FriendsPanelScroll = new javax.swing.JScrollPane();
        FriendsPanel = new javax.swing.JPanel();
        CoverPhotoPanel = new javax.swing.JPanel();
        ProfilePhotoPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        settings = new javax.swing.JButton();
        retrun = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Profile Page");

        jLayeredPane1.setBackground(new java.awt.Color(153, 0, 255));

        javax.swing.GroupLayout PostsPanelLayout = new javax.swing.GroupLayout(PostsPanel);
        PostsPanel.setLayout(PostsPanelLayout);
        PostsPanelLayout.setHorizontalGroup(
            PostsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 472, Short.MAX_VALUE)
        );
        PostsPanelLayout.setVerticalGroup(
            PostsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 382, Short.MAX_VALUE)
        );

        PostsPanelScroll.setViewportView(PostsPanel);

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

        jLabel1.setText("jLabel1");

        jLabel2.setText("Bio");

        settings.setText("Settings");
        settings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsActionPerformed(evt);
            }
        });

        retrun.setText("Return");
        retrun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                retrunActionPerformed(evt);
            }
        });

        jLayeredPane1.setLayer(PostsPanelScroll, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(FriendsPanelScroll, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(CoverPhotoPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(ProfilePhotoPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(settings, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(retrun, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addComponent(PostsPanelScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(FriendsPanelScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(CoverPhotoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(ProfilePhotoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 388, Short.MAX_VALUE)
                                .addComponent(retrun))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(settings)))))
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
                        .addComponent(settings)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2))
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(retrun)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PostsPanelScroll)
                    .addComponent(FriendsPanelScroll)))
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

    private void settingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsActionPerformed
        SettingsWindow settingsWindow = new SettingsWindow(this);
        settingsWindow.setVisible(true);
        settingsWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_settingsActionPerformed

    private void retrunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_retrunActionPerformed
        NewsFeedWindow newsfeedWindow = new NewsFeedWindow();
        newsfeedWindow.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_retrunActionPerformed

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
            java.util.logging.Logger.getLogger(Profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        UsersDatabase database = new UsersDatabase();
        database.loadUsers();
        

        // Step 1: Create a new list of users
        java.util.List<User> users = new ArrayList<>();

        // Add some users to the list
        User user1 = new User(
                "johndoe@example.com",
                "johndoe",
                "password123",
                "1995-05-15",
                "path/to/photo.jpg",
                "path/to/cover.jpg",
                "Hello, I am John Doe!"
        );
        user1.setStatus(true);

        User user2 = new User(
                "janedoe@example.com",
                "janedoe",
                "password456",
                "1998-03-22",
                "path/to/photo2.jpg",
                "path/to/cover2.jpg",
                "Hi, I'm Jane Doe!"
        );
        user2.setStatus(false);

        User user3 = new User(
                "janedoe@example.com",
                "janedoe",
                "password456",
                "1998-03-22",
                "path/to/photo2.jpg",
                "path/to/cover2.jpg",
                "Hi, I'm Jene Doe!"
        );
        user3.setStatus(true);

        // Establish friendship between user1 and user2
        user1.addFriend(user2);
        user2.addFriend(user1);
        user1.addFriend(user3);
        user3.addFriend(user1);
        user3.addFriend(user2);
        user2.addFriend(user3);
        // Add users to the list
        users.add(user1);
        users.add(user2);
        users.add(user3);

        // Step 2: Save the users to a JSON file
        database.saveUsers(users);
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Profile(user1).setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CoverPhotoPanel;
    private javax.swing.JPanel FriendsPanel;
    private javax.swing.JScrollPane FriendsPanelScroll;
    private javax.swing.JPanel PostsPanel;
    private javax.swing.JScrollPane PostsPanelScroll;
    private javax.swing.JPanel ProfilePhotoPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JButton retrun;
    private javax.swing.JButton settings;
    // End of variables declaration//GEN-END:variables
}
