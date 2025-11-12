package view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MainView extends JFrame {

    private static final String WINDOW_TITLE = "Aquarium-MVP";
    private int windowWidth;
    private int windowHeight;

    // "Akvarium"
    JLayeredPane container = new JLayeredPane();
    // "Dekoration"?
    JPanel panel = new JPanel();

    //Temporär bakgrund
    //JLabel bakgrund = new JLabel(new ImageIcon("./src/view/images/akvarium1.jpg"));
    JLabel bakgrund = new JLabel();

    ImageIcon OGimage = new ImageIcon("./src/view/images/akvarium1.jpg");
    Image scaledImage = OGimage.getImage().getScaledInstance(960, 540, Image.SCALE_SMOOTH);



    public MainView(int windowWidth, int windowHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        initComponents();
    }



    private void initComponents() {
        this.setTitle(WINDOW_TITLE);
        setSize(new Dimension(windowWidth, windowHeight));
        // Lägger Fönstret i mitten av skärmen
        this.setLocationRelativeTo(null);

        // LayeredPanes characteristics
        container.setBounds(160,90,960, 540);
        container.setOpaque(true);

        // lägg till Akvarium till Fönstret
        this.add(container);

        // Lägger till bilden till bakgrund(JLabel)
        bakgrund.setIcon(new ImageIcon(scaledImage));
        bakgrund.setBounds(0,0,960,540);
        container.add(bakgrund, Integer.valueOf(0));

        // Dekorationens characteristics
        //panel.setSize(new Dimension(200,200));
        panel.setBounds(0,330,200, 200);
        panel.setBackground(Color.green);
        // Lägger till Dekorationen till Akvariet
        container.add(panel, Integer.valueOf(1));

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
        fisk.setBounds(100, 400, 50, 50);
        fisk.setOpaque(false);
        // Lägger till fisken till akvariet
        container.add(fisk, Integer.valueOf(2));

        //this.pack();
        this.setLayout(null);

        this.setVisible(true);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setResizable(false);

    }






}
