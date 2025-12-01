package com.grupp26.aquasim.view;

import javax.swing.*;
import java.util.List;

public class MainView extends JFrame {

    private static final String WINDOW_TITLE = "Aquarium-MVP";
    private int windowWidth;
    private int windowHeight;
    private DrawPanel drawPanel;

    // bara testar att koppla en lyssnare till denna knapp
    private final JButton addFishButton = new JButton("Add fish");

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

        drawPanel = new DrawPanel(windowWidth, windowHeight);
        drawPanel.setOpaque(true);

        this.add(drawPanel);
        drawPanel.repaint();

        this.setVisible(true);
    }

    public void addEntity(IRenderedEntity e) {
        drawPanel.addEntity(e);
    }

    public void removeEntity(IRenderedEntity e) {
        drawPanel.removeEntity(e);
    }

    @Override
    public void repaint() {
        super.repaint();

        drawPanel.repaint();
    }



    public JButton getAddFish() {
        return this.addFishButton;
    }

}
