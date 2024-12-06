/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FrontEnd;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author John
 */
public class Post extends JPanel {

    private String text;
    private String imgDir;
    private MyCanvas img;
    private javax.swing.JLabel PostTextLbl;

    private static int yPosition = 10;

    /**
     * Creates new form Post
     *
     * @param text
     * @param imgDir
     */
    public Post(String text, String imgDir) {
        this.text = text;
        this.imgDir = imgDir;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        PostTextLbl = new javax.swing.JLabel(text);
        add(PostTextLbl);
        System.out.println("label: " + PostTextLbl.getHeight());

        if (imgDir != null && !imgDir.trim().isEmpty()) {
            img = new MyCanvas(imgDir);
//        img.setBounds(6, 105, 10, 10);
//            img.setBackground(Color.BLUE);
            img.setPreferredSize(new Dimension(800, 200));
            img.setMaximumSize(new Dimension(800, 200));
            img.setAlignmentX(CENTER_ALIGNMENT);
            add(img);
        }

//        setBounds(10, yPosition, 550, img.getHeight() + PostTextLbl.getHeight() + 40);
        setPreferredSize(new Dimension(550, PostTextLbl.getPreferredSize().height + (img != null ? img.getPreferredSize().height : 0) + 40));
        setMaximumSize(new Dimension(550, getPreferredSize().height));
//        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

//        setBackground(Color.GRAY);
        yPosition += getHeight() + 10;
        setMaximumSize(new Dimension(550, getPreferredSize().height));
        setVisible(true);
    }
}
