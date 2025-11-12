package view;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {

    private static final String WINDOW_TITLE = "Aquarium-MVP";
    private int windowWidth;
    private int windowHeight;


    private final JPanel aquarium = new JPanel();



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
        this.setIconImage(new ImageIcon("icon-grupp26.png").getImage());
        // Fungerar oavsett var programmet körs eller om det packas till .jar
        // this.setIconImage(new ImageIcon(getClass().getResource("/images/logo.png")).getImage());



        // Kommenterad tills jag lär mig BorderLayout
        // aquarium.setLayout(new BorderLayout());

        aquarium.setBackground(Color.blue);
        aquarium.setBounds(160, 90,960, 540);


        this.add(aquarium);


        //this.pack();
        this.setLayout(null);

        this.setVisible(true);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setResizable(false);
    }




}
