package com.grupp26.aquasim.controller;

import com.grupp26.aquasim.model.ModelFacade;

import com.grupp26.aquasim.view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;

public class Controller implements IController {
    // offset to make it feel more resposive, placing it at the mouse instead of
    // down to the right
    private static final int MOUSE_OFFSET = 25;

    ModelFacade modelFacade;
    MainView view;
    int mouseX;
    int mouseY;
    MouseMode mouseMode = MouseMode.NONE;
    Map<MouseMode, JButton> buttons = new HashMap<MouseMode, JButton>();

    public Controller(ModelFacade modelFacade, MainView view) {
        this.modelFacade = modelFacade;
        this.view = view;
        registerButtons();
        initListeners();
    }

    private enum MouseMode {
        NONE,
        FOOD,
        FISH,

    }

    private void registerButtons() {
        buttons.put(MouseMode.FISH, view.getAddFishButton());
        buttons.put(MouseMode.FOOD, view.getAddFoodButton());
    }

    // Controller reggar sig själv som lyssnare på addFish-knappen i view
    private void initListeners() {

        // Anonym ActionListener
        view.getAddFishButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleMouseState(MouseMode.FISH);
            }
        });

        view.getRemoveFishButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modelFacade.removeFish();
            }
        });

        view.getAddFoodButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                handleMouseState(MouseMode.FOOD);
            }
        });

        view.getDrawPanel().addMouseMotionListener((MouseMotionListener) new MouseMotionAdapter() {

            @Override
            public void mouseMoved(MouseEvent e) {
                mouseX = e.getX() - MOUSE_OFFSET;
                mouseY = e.getY() - MOUSE_OFFSET;
            }
        });

        view.getDrawPanel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (mouseMode == MouseMode.FOOD) {
                    modelFacade.addFood("base", mouseX, mouseY);
                } else if (mouseMode == MouseMode.FISH) {
                    modelFacade.addFish(mouseX, mouseY);
                }
            }
        });
    }

    private void handleMouseState(MouseMode mode) {
        if (mouseMode == mode) {
            mouseMode = MouseMode.NONE;
            view.setInActive(buttons.get(mode));
            return;
        }

        for (JButton button : buttons.values()) {
            view.setInActive(button);
        }

        view.setActive(buttons.get(mode));

        mouseMode = mode;
    }

}
