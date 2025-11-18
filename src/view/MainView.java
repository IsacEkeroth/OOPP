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
    DrawPanel drawPanel = new DrawPanel(getImage("./src/view/images/icon-grupp26.png"), 300, 300);

    // Temporär bakgrund
    // JLabel bakgrund = new JLabel(new
    // ImageIcon("./src/view/images/akvarium1.jpg"));
    JLabel bakgrund = new JLabel();

    ImageIcon OGimage = new ImageIcon("./src/view/images/akvarium1.jpg");
    Image scaledImage = OGimage.getImage().getScaledInstance(960, 540, Image.SCALE_SMOOTH);

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

        // Lägger till bilden till bakgrund(JLabel)
        bakgrund.setIcon(new ImageIcon(scaledImage));
        bakgrund.setBounds(0, 0, 960, 540);
        container.add(bakgrund, Integer.valueOf(0));

        drawPanel.setBounds(200, 200, 200, 200);
        drawPanel.setBackground(Color.red);
        drawPanel.setOpaque(true);

        container.add(drawPanel, Integer.valueOf(1));

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
