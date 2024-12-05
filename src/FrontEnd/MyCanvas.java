package FrontEnd;

import java.awt.*;
import javax.swing.*;

public class MyCanvas extends JComponent {

    private String path = "D:\\Photos\\9A8CC6BC-C550-43B2-B70B-B435FEA779F6.jpg";

    /**
     *
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (path == null || path.trim().isEmpty()) {
            return;  // Skip painting if no valid image
        }
        Toolkit t = Toolkit.getDefaultToolkit();
        Image i = t.getImage(path);

        // Wait until the image is fully loaded
        MediaTracker tracker = new MediaTracker(this);
        tracker.addImage(i, 0);
        try {
            tracker.waitForAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Draw image centered
        int newWidth = 0, newHeight = 0;
        if (getWidth() > getHeight()) {
            newHeight = getHeight();
            newWidth = (i.getWidth(this) * getHeight()) / i.getHeight(this);
        } else {
            newWidth = getWidth();
            newHeight = (i.getHeight(this) * getWidth()) / i.getWidth(this);
        }
        g.drawImage(i, (getWidth() / 2) - (newWidth / 2), (getHeight() / 2) - (newHeight / 2), newWidth, newHeight, this);
//        g.drawImage(i, 0, 0, newWidth, newHeight, this);
    }

    public MyCanvas(String path) {
        this.path = path;
        repaint();
    }

    public void changePicture(String path) {
        this.path = path;
        this.repaint();
    }

    /**
     *
     * @return
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(520, 200);
    }
}
