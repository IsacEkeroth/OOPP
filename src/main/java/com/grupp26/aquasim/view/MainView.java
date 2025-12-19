package com.grupp26.aquasim.view;

import com.grupp26.aquasim.controller.ActiveMode;
import com.grupp26.aquasim.model.IEntity;
import com.grupp26.aquasim.model.IModelFacade;
import com.grupp26.aquasim.view.DecorationSelectionListener;

import javafx.embed.swing.JFXPanel;

import javax.swing.*;
import java.awt.*;

import java.util.*;

public class MainView extends JFrame implements IMainView {

    private static final String WINDOW_TITLE = "Aquarium-MVP";
    private int windowWidth;
    private int windowHeight;
    private DrawPanel drawPanel;
    private IModelFacade facade;

    private JButton selectedButton = null;

    private Map<ActiveMode, JButton> selectableButtons = new HashMap<ActiveMode, JButton>();
    private Map<String, JButton> fishButtons = new HashMap<>();
    private Map<String, JButton> foodButtons = new HashMap<>();
    private Map<String, JButton> decorationButtons = new HashMap<>();
    private Map<String, Integer> entityAnimationCounter = new HashMap<>();

    private final JPanel controlPanel = new JPanel();
    private final JButton addFishButton = new JButton("Add fish");
    private final JButton addFoodButton = new JButton("Add food");
    private final JButton removeFishButton = new JButton("Remove fish");
    private final JButton addDecorationButton = new JButton("Add decoration");

    private final JButton goldFishButton = new JButton("GoldFish");
    private final JButton clownFishButton = new JButton("ClownFish");

    private final JButton baseFoodButton = new JButton("Base");
    private final JButton loveFoodButton = new JButton("Lovefood");

    private final JButton anchorButton = new JButton("Anchor");
    private final JButton seaweedButton = new JButton("Seaweed");

    private final JPanel fishMenuPanel = new JPanel();
    private final JPanel foodMenuPanel = new JPanel();
    private final JPanel decorationMenuPanel = new JPanel();

    public MainView(int windowWidth, int windowHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        registerButtons();
        initComponents();
    }

    private void registerButtons() {
        selectableButtons.put(ActiveMode.FISH_MENU, this.getAddFishButton());
        selectableButtons.put(ActiveMode.FOOD_MENU, this.getAddFoodButton());
        selectableButtons.put(ActiveMode.DECORATION_MENU, this.getDecorationButton());

        fishButtons.put("GoldFish", this.getGoldFishButton());
        fishButtons.put("ClownFish", this.getClownFishButton());

        foodButtons.put("Base", this.getBaseFoodButton());
        foodButtons.put("Lovefood", this.getLoveFoodButton());

        decorationButtons.put("Anchor", this.getAnchorButton());
        decorationButtons.put("Seaweed", this.getSeaweedButton());
    }

    private void initComponents() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle(WINDOW_TITLE);
        this.setSize(windowWidth, windowHeight);

        drawPanel = new DrawPanel(windowWidth, windowHeight);
        drawPanel.setOpaque(true);
        drawPanel.setLayout(new BorderLayout());

        initFishSelectionPanel();
        initFoodSelectionPanel();
        initDecorationSelectionPanel();

        controlPanel.setLayout(new GridLayout(1, 4));
        controlPanel.setOpaque(false);
        controlPanel.add(addFishButton, 0);
        controlPanel.add(addFoodButton, 1);
        controlPanel.add(removeFishButton, 2);
        controlPanel.add(addDecorationButton, 3);

        JPanel buttonPanel = new JPanel(
                new FlowLayout(FlowLayout.LEFT, 0, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.add(controlPanel);

        drawPanel.add(buttonPanel, BorderLayout.SOUTH);
        this.add(drawPanel);
        drawPanel.repaint();

        this.setVisible(true);

        // Audio setup
        JFXPanel jFXPanel = new JFXPanel();
        this.add(jFXPanel);
    }

    private void initFoodSelectionPanel() {
        foodMenuPanel.setLayout(new GridLayout(1, 2, 0, 0));
        foodMenuPanel.setOpaque(false);
        foodMenuPanel.add(baseFoodButton);
        foodMenuPanel.add(loveFoodButton);
        foodMenuPanel.setBounds(10, windowHeight - 170, 250, 50);
        foodMenuPanel.setVisible(false);
        drawPanel.add(foodMenuPanel, BorderLayout.SOUTH);
    }

    private void initDecorationSelectionPanel() {
        decorationMenuPanel.setLayout(new GridLayout(1, 2, 0, 0));
        decorationMenuPanel.setOpaque(false);
        decorationMenuPanel.add(anchorButton);
        decorationMenuPanel.add(seaweedButton);
        decorationMenuPanel.setBounds(10, windowHeight - 230, 250, 50);
        decorationMenuPanel.setVisible(false);
        drawPanel.add(decorationMenuPanel, BorderLayout.SOUTH);
    }

    public JPanel getFoodMenuPanel() {
        return this.foodMenuPanel;
    }

    public JButton getLoveFoodButton() {
        return this.loveFoodButton;
    }

    private void initFishSelectionPanel() {
        fishMenuPanel.setLayout(new GridLayout(1, 2, 0, 0));
        fishMenuPanel.setOpaque(false);

        fishMenuPanel.add(goldFishButton);
        fishMenuPanel.add(clownFishButton);

        fishMenuPanel.setBounds(10, windowHeight - 110, 250, 50);
        fishMenuPanel.setVisible(false); // Gör den osynlig, så den kan togglas rätt sen
        drawPanel.add(fishMenuPanel, BorderLayout.SOUTH);
    }

    public JPanel getFishMenuPanel() {
        return this.fishMenuPanel;
    }

    @Override
    public void addFishMenuListener(FishSelectionListener listener) {
        for (Component comp : fishMenuPanel.getComponents()) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                button.addActionListener(e -> {
                    listener.onFishSelected(button.getText());
                });
            }
        }
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

    public JButton getGoldFishButton() {
        return this.goldFishButton;
    }

    public JButton getClownFishButton() {
        return clownFishButton;
    }

    public JButton getAddFoodButton() {
        return this.addFoodButton;
    }

    @Override
    public JButton getRemoveFishButton() {
        return this.removeFishButton;
    }

    public JButton getBaseFoodButton() {
        return this.baseFoodButton;
    }

    public void addFoodMenuListener(FoodSelectionListener listener) {
        for (Component comp : foodMenuPanel.getComponents()) {
            if (comp instanceof JButton button) {
                button.addActionListener(e -> listener.onFoodSelected(button.getText()));
            }
        }
    }

    public JPanel getDecorationMenuPanel() {
        return this.decorationMenuPanel;
    }

    public JButton getAnchorButton() {
        return this.anchorButton;
    }

    public JButton getSeaweedButton() {
        return this.seaweedButton;
    }

    public DrawPanel getDrawPanel() {
        return this.drawPanel;
    }

    private void setActive(JButton button) {
        button.setForeground(Color.GREEN);
    }

    private void setInActive(JButton button) {
        if (button != null) {
            button.setForeground(Color.BLACK);
        }
    }

    public void updateActiveButton(ActiveMode mode, String type) {
        selectedButton = selectableButtons.get(mode);
        for (JButton button : selectableButtons.values()) {
            if (button != null)
                setInActive(button);
        }
        for (JButton button : fishButtons.values()) {
            if (button != null)
                setInActive(button);
        }
        for (JButton button : foodButtons.values()) {
            if (button != null)
                setInActive(button);
        }

        for (JButton button : decorationButtons.values()) {
            if (button != null)
                setInActive(button);
        }

        // hide menus
        if (mode == ActiveMode.FISH_MENU) {
            boolean show = !fishMenuPanel.isVisible();
            showOnlyMenu(show ? fishMenuPanel : null);
            if (show) {
                setActive(addFishButton);
            }
        } else if (mode == ActiveMode.PLACING_FISH) {
            showOnlyMenu(fishMenuPanel);
            setActive(addFishButton);
            if (fishButtons.containsKey(type)) {
                setActive(fishButtons.get(type));
            }
        } else if (mode == ActiveMode.FOOD_MENU) {
            boolean show = !foodMenuPanel.isVisible();
            showOnlyMenu(show ? foodMenuPanel : null);
            if (show) {
                setActive(addFoodButton);
            }
        } else if (mode == ActiveMode.PLACING_FOOD) {
            showOnlyMenu(foodMenuPanel);
            setActive(addFoodButton);
            if (foodButtons.containsKey(type)) {
                setActive(foodButtons.get(type));
            }
        } else if (mode == ActiveMode.DECORATION_MENU) {
            boolean show = !decorationMenuPanel.isVisible();
            showOnlyMenu(show ? decorationMenuPanel : null);
            if (show) {
                setActive(addDecorationButton);
            }
        } else if (mode == ActiveMode.PLACING_DECORATION) {
            showOnlyMenu(decorationMenuPanel);
            setActive(addDecorationButton);
            if (decorationButtons.containsKey(type)) {
                setActive(decorationButtons.get(type));
            }
        } else {
            showOnlyMenu(null);
        }
    }

    private void showOnlyMenu(JPanel menuToShow) {
        fishMenuPanel.setVisible(false);
        foodMenuPanel.setVisible(false);
        decorationMenuPanel.setVisible(false);
        if (menuToShow != null) {
            menuToShow.setVisible(true);
        }
    }

    public JButton getDecorationButton() {
        return this.addDecorationButton;
    }

    @Override
    public void addDecorationMenuListener(DecorationSelectionListener listener) {
        for (Component comp : decorationMenuPanel.getComponents()) {
            if (comp instanceof JButton button) {
                button.addActionListener(e -> listener.onDecorationSelected(button.getText()));
            }
        }
    }
}
