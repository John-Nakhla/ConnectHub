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
public class Story extends JPanel {

    private String text;
    private String imgDir;
    private MyCanvas img;
    private JLabel storyTextLbl;

    public Story(String text, String imgDir) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(150, 100));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        if (text != null && !text.trim().isEmpty()) {
            storyTextLbl = new JLabel("<html><center>" + text + "</center></html>");
            storyTextLbl.setHorizontalAlignment(SwingConstants.CENTER);
            add(storyTextLbl);
        } else if (imgDir != null && !imgDir.trim().isEmpty()) {
            img = new MyCanvas(imgDir);
            img.setPreferredSize(new Dimension(100, 100)); // Smaller size for stories
            add(img);
        }

        setMaximumSize(new Dimension(150, 150));
    }

}
