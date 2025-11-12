package view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MainView extends JFrame {

    private static final String WINDOW_TITLE = "Aquarium-MVP";
    private int windowWidth;
    private int windowHeight;

    JLayeredPane container = new JLayeredPane();

    JPanel panel = new JPanel();


    public MainView(int windowWidth, int windowHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        initComponents();
    }



    private void initComponents() {
        this.setTitle(WINDOW_TITLE);
        setSize(new Dimension(windowWidth, windowHeight));

        container.setBackground(Color.BLUE);
        container.setOpaque(true);

        container.setBounds(160,90,960, 540);
        this.add(container);


        panel.setSize(new Dimension(200,200));
        panel.setBackground(Color.green);
        container.add(panel);

        // Temporärt
        // Load original image
        ImageIcon originalIcon = new ImageIcon("./src/view/images/icon-grupp26.png");
        BufferedImage originalImage = new BufferedImage(
                originalIcon.getIconWidth(),
                originalIcon.getIconHeight(),
                BufferedImage.TYPE_INT_ARGB);

        Graphics g = originalImage.getGraphics();
        g.drawImage(originalIcon.getImage(), 0, 0, null);
        g.dispose();
        // Scale the image to desired dimensions
        Image scaledImage = originalImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel fisk = new JLabel(scaledIcon);
        fisk.setBounds(100, 100, 50, 50);
        fisk.setOpaque(false);
        container.add(fisk, Integer.valueOf(1));

        //this.pack();
        this.setLayout(null);

        this.setVisible(true);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setResizable(false);
    }




}
