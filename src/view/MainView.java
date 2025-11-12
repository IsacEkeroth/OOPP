package view;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {

    private static final String WINDOW_TITLE = "Aquarium-MVP";
    private int windowWidth;
    private int windowHeight;


    private final JPanel aquarium = new JPanel();
    private final JPanel test = new JPanel();


    public MainView(int windowWidth, int windowHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        initComponents();
    }



    private void initComponents() {
        this.setTitle(WINDOW_TITLE);
        //this.setPreferredSize(new Dimension(windowWidth, windowHeight));
        setSize(new Dimension(windowWidth, windowHeight));
        // Funkar bara om bilden ligger i samma mapp där projektet körs
        this.setIconImage(new ImageIcon("./src/view/images/icon-grupp26.png").getImage());
        // Fungerar oavsett var programmet körs eller om det packas till .jar
        // this.setIconImage(new ImageIcon(getClass().getResource("/images/logo.png")).getImage());



        // Kommenterad tills jag lär mig BorderLayout
        // aquarium.setLayout(new BorderLayout());

        aquarium.setBackground(Color.blue);
        aquarium.setBounds(160, 90,200, 200);


        test.setBackground(Color.red);
        test.setBounds(190, 90,200, 200);


        //this.add(aquarium);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBackground(Color.black);
        layeredPane.setOpaque(true);
        //layeredPane.setSize(new Dimension(300, 310));
        //layeredPane.setBackground(Color.red);
        layeredPane.setBounds(0,0,960, 540);
        this.add(layeredPane);



        layeredPane.add(aquarium, Integer.valueOf(1));
        layeredPane.add(test, Integer.valueOf(2));

        JLabel fisk = new JLabel(new ImageIcon("./src/view/images/icon-grupp26.png"));
        fisk.setBounds(100, 100, 50, 50);
        layeredPane.add(fisk);

        //this.pack();
        this.setLayout(null);

        this.setVisible(true);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setResizable(false);

        this.repaint();
    }




}
