package com.grupp26.aquasim.view;

import com.grupp26.aquasim.model.IEntity;
import com.grupp26.aquasim.model.ModelFacade;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainView extends JFrame implements IObserver {

    private static final String WINDOW_TITLE = "Aquarium-MVP";
    private int windowWidth;
    private int windowHeight;
    private DrawPanel drawPanel;
    private ModelFacade facade;

    // controlPanel för framtida knappar
    private final JPanel controlPanel = new JPanel();
    private final JButton addFishButton = new JButton("Add fish");
    private final JButton feedFishButton = new JButton("Feed fish");


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
        // Denna behövdes lägga till
        drawPanel.setLayout(null);

        controlPanel.setLayout(new GridLayout(1,2));
        controlPanel.add(addFishButton,0);
        controlPanel.add(feedFishButton,1);
        controlPanel.setBackground(Color.BLACK);
        // Placering av controlPanel i fönstret
        int buttonWidth = 150;
        int buttonHeight = 50;
        controlPanel.setBounds(10,windowHeight-90,buttonWidth,buttonHeight);

        // Lägger controlPanel PÅ drawPanel
        drawPanel.add(controlPanel);
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
    
    @Override
    public void update() {
        drawPanel.clear();
        
        ArrayList<IEntity> modelEntities = new ArrayList<>(facade.getEntities());
        for (IEntity e : modelEntities) {
            addEntity(new RenderedEntity(e));
        }
        repaint();
    }
    
    public void setFacade(ModelFacade facade) {
        this.facade = facade;
    }



    public JButton getAddFish() {
        return this.addFishButton;
    }

}
