package FrontEnd;

import java.awt.*;
import javax.swing.JFrame;

public class MyCanvas extends Canvas {

    private String path = "D:\\Photos\\9A8CC6BC-C550-43B2-B70B-B435FEA779F6.jpg";

    @Override
    public void paint(Graphics g) {
        Toolkit t = Toolkit.getDefaultToolkit();
        Image i = t.getImage(path);

        int newWidth = 0, newHeight = 0;
        if (getWidth() > getHeight()) {
            newHeight = getHeight();
            newWidth = (i.getWidth(this) * getHeight()) / i.getHeight(this);
        } else {
            newWidth = getWidth();
            newHeight = (i.getHeight(this) * getWidth()) / i.getWidth(this);
        }
        g.drawImage(i, (getWidth() / 2) - (newWidth / 2), (getHeight()/ 2) - (newHeight / 2), newWidth, newHeight, this);
    }

    public static void main(String[] args) {
        MyCanvas m = new MyCanvas();
        JFrame f = new JFrame();
        f.add(m);
        f.setSize(400, 400);
        f.setVisible(true);
    }

    public void changePicture(String path) {
        this.path = path;
        this.repaint();
    }
}
