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

    // "Akvarium"
    JLayeredPane container = new JLayeredPane();

    // "Fiskar och dekorationer"
    DrawPanel drawPanel;

    DrawPanel background;

    public MainView(int windowWidth, int windowHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        initComponents();
    }

    private void initComponents() {
        this.setLayout(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle(WINDOW_TITLE);
        setSize(new Dimension(windowWidth, windowHeight));

        // Lägger Fönstret i mitten av skärmen
        this.setLocationRelativeTo(null);

        // LayeredPanes characteristics
        container.setBounds(160, 90, 960, 540);
        container.setOpaque(true);

        // lägg till Akvarium till Fönstret
        this.add(container);

        Entity bgEntity = new Entity(new Point(0, 0), 0, new Point(this.windowWidth, this.windowHeight),
                getImage("./src/view/images/akvarium1.jpg"));

        Entity f1 = new Entity(new Point(0, 0), 1, new Point(50, 50),
                getImage("./src/view/images/icon-grupp26.png"));
        Entity f2 = new Entity(new Point(200, 200), 1, new Point(50, 50),
                getImage("./src/view/images/icon-grupp26.png"));

        drawPanel = new DrawPanel(this.windowWidth, this.windowHeight);

        drawPanel.setBounds(0, 0, this.windowWidth, this.windowHeight);
        drawPanel.setOpaque(true);

        drawPanel.addEntity(bgEntity);
        drawPanel.addEntity(f1);
        drawPanel.addEntity(f2);

        drawPanel.repaint();

        container.add(drawPanel, Integer.valueOf(0));
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
