package com.grupp26.aquasim.view;

import com.grupp26.aquasim.controller.ActiveMode;
import com.grupp26.aquasim.model.IEntity;
import com.grupp26.aquasim.model.IModelFacade;

import javafx.embed.swing.JFXPanel;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainView extends JFrame implements IMainView {

    private static final String WINDOW_TITLE = "Aquarium-MVP";
    private int windowWidth;
    private int windowHeight;
    private DrawPanel drawPanel;
    private IModelFacade facade;

    private JButton selectedButton = null;
    Map<ActiveMode, JButton> selectableButtons = new HashMap<ActiveMode, JButton>();

    // TODO Mappar varje entityID till nuvarande tick för hela simulationen
    // Agerar som "minne" för view
    // Varje entityID som ska ritas är mappad till ett tick.
    private Map<String, Integer> entityAnimationCounter = new HashMap<>();

    // controlPanel för framtida knappar
    private final JPanel controlPanel = new JPanel();
    private final JButton addFishButton = new JButton("Add fish");
    private final JButton addFoodButton = new JButton("Food mode");
    private final JButton removeFishButton = new JButton("Remove fish");
    private final JButton addDecorationButton = new JButton("Add decoration");

    public MainView(int windowWidth, int windowHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        registerButtons();
        initComponents();
    }

    private void registerButtons() {
        selectableButtons.put(ActiveMode.FISH, this.getAddFishButton());
        selectableButtons.put(ActiveMode.FOOD, this.getAddFoodButton());
    }

    private void initComponents() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle(WINDOW_TITLE);
        this.setSize(windowWidth, windowHeight);

        drawPanel = new DrawPanel(windowWidth, windowHeight);
        drawPanel.setOpaque(true);

        // TODO -- Bättre att ha LayoutManager? Bör vi ändra? --
        // Denna behövdes lägga till, så vi har ingen layoutmanager.
        // Vi använder absolute positioning.
        drawPanel.setLayout(null);

        controlPanel.setLayout(new GridLayout(1, 4));
        controlPanel.add(addFishButton, 0);
        controlPanel.add(addFoodButton, 1);
        controlPanel.add(removeFishButton, 2);
        controlPanel.add(addDecorationButton, 3);
        controlPanel.setBackground(Color.BLACK);

        int buttonWidth = 500;
        int buttonHeight = 50;
        // TODO -- Ändra så vi INTE använder Absolute Positioning --
        // Placering av controlPanel på (x, y) i drawPanel
        controlPanel.setBounds(10, windowHeight - 90, buttonWidth, buttonHeight);

        // Lägger controlPanel PÅ drawPanel
        this.add(drawPanel);
        drawPanel.add(controlPanel);
        drawPanel.repaint();

        this.setVisible(true);

        // Audio setup
        JFXPanel jFXPanel = new JFXPanel();
        this.add(jFXPanel);
    }

    public void addRenderedEntity(IRenderedEntity e) {
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

        // Set över aktiva IDs i denna uppdatering, så vi inte får dubbletter
        Set<String> activeIDs = new HashSet<>();

        for (IEntity e : modelEntities) {
            // Hämtar ID för given entity, lägger till i mappen
            String entityID = e.getEntity_ID();
            activeIDs.add(entityID);

            // Hämtar nuvarande tick för given entity
            int currentTick = entityAnimationCounter.getOrDefault(entityID, 0);

            IRenderedEntity renderedEntity = new RenderedEntity(e, currentTick);
            addRenderedEntity(renderedEntity);

            // Öka värdet till nästa uppdatering
            entityAnimationCounter.put(entityID, currentTick + 1);
        }
        // Ta bort alla ID från mappen som inte längre finns i modellen
        // Använder HashSet:et som "mall" för att enbart behålla dom och inget annat.
        entityAnimationCounter.keySet().retainAll(activeIDs);

        repaint();
    }

    public void setFacade(IModelFacade facade) {
        this.facade = facade;
    }

    public JButton getAddFishButton() {
        return this.addFishButton;
    }

    public JButton getAddFoodButton() {
        return this.addFoodButton;
    }

    @Override
    public JButton getRemoveFishButton() {
        return this.removeFishButton;
    }

    public DrawPanel getDrawPanel() {
        return this.drawPanel;
    }

    private void setActive(JButton button) {
        button.setForeground(Color.GREEN);
    }

    private void setInActive(JButton button) {
        button.setForeground(Color.BLACK);
    }

    public void updateActiveButton(ActiveMode mode) {
        selectedButton = selectableButtons.get(mode);

        for (JButton button : selectableButtons.values()) {
            setInActive(button);
        }

        if (mode != ActiveMode.NONE) {
            setActive(selectedButton);
        }
    }

    public JButton getDecorationButton() {
        return this.addDecorationButton;
    }
}
