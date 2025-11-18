package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainView extends JFrame {

    private static final String WINDOW_TITLE = "Aquarium-MVP";
    private int windowWidth;
    private int windowHeight;
    private DrawPanel drawPanel;

    public MainView(int windowWidth, int windowHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        initComponents();
    }

    private void initComponents() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle(WINDOW_TITLE);
        this.setSize(windowWidth, windowHeight);
        this.setLocationRelativeTo(null);

        drawPanel = new DrawPanel(windowWidth, windowHeight);
        drawPanel.setOpaque(true);

        Entity bgEntity = new Entity(new Point(0, 0), 0, new Point(windowWidth, windowHeight),
                getImage("./src/view/images/akvarium1.jpg"));
        Entity f1 = new Entity(new Point(0, 0), 1, new Point(50, 50),
                getImage("./src/view/images/icon-grupp26.png"));
        Entity f2 = new Entity(new Point(200, 200), 1, new Point(50, 50),
                getImage("./src/view/images/icon-grupp26.png"));

        drawPanel.addEntity(bgEntity);
        drawPanel.addEntity(f1);
        drawPanel.addEntity(f2);

        this.add(drawPanel);
        drawPanel.repaint();

        this.setVisible(true);
    }

    private BufferedImage getImage(String path) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
            img = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB); // fallback
        }
        return img;
    }
}
