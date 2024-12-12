/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FrontEnd;

import connecthub.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

public class ReviewPendingPosts extends JFrame {

    public ReviewPendingPosts(GroupAdmin admin, Group group) {
        setTitle("Pending Posts");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 500);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Fetch pending posts from the database
        PostsDatabase postsDb = new PostsDatabase();
        List<Posts> pendingPosts = postsDb.getGroupPosts(group.getGroupName());

        for (Posts post : pendingPosts) {
            if ("Pending".equals(post.getStatus())) {
                // Panel for each post
                JPanel postPanel = new JPanel(new BorderLayout());
                JLabel contentLabel = new JLabel("<html><b>Content:</b> " + post.getContent() + "</html>");
                JLabel authorLabel = new JLabel("<html><b>Author:</b> " + post.getUsername() + "</html>");

                JPanel detailsPanel = new JPanel(new GridLayout(2, 1));
                detailsPanel.add(contentLabel);
                detailsPanel.add(authorLabel);

                // Buttons for actions
                JButton viewButton = new JButton("View Details");
                JButton approveButton = new JButton("Approve");
                JButton declineButton = new JButton("Decline");

                // View Button Action
                viewButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Display full post details
                        JOptionPane.showMessageDialog(ReviewPendingPosts.this,
                                "<html><b>Author:</b> " + post.getUsername() +
                                        "<br><b>Content:</b> " + post.getContent() +
                                        (post.getImg() != null && !post.getImg().isEmpty() ?
                                                "<br><b>Image:</b> <br><img src='" + post.getImg() + "'>" : ""),
                                "Post Details",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                });

                // Approve Button Action
                approveButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        admin.approvePost(post.getPostId());
                        JOptionPane.showMessageDialog(ReviewPendingPosts.this, "Post Approved");
                        mainPanel.remove(postPanel);
                        mainPanel.revalidate();
                        mainPanel.repaint();
                    }
                });

                // Decline Button Action
                declineButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        admin.rejectPost(post.getPostId());
                        JOptionPane.showMessageDialog(ReviewPendingPosts.this, "Post Declined");
                        mainPanel.remove(postPanel);
                        mainPanel.revalidate();
                        mainPanel.repaint();
                    }
                });

                JPanel buttonPanel = new JPanel();
                buttonPanel.add(viewButton);
                buttonPanel.add(approveButton);
                buttonPanel.add(declineButton);

                postPanel.add(detailsPanel, BorderLayout.CENTER);
                postPanel.add(buttonPanel, BorderLayout.SOUTH);

                mainPanel.add(postPanel);
            }
        }

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane);
    }
}




