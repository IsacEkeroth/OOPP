package com.grupp26.aquasim.controller;

import com.grupp26.aquasim.ISoundObservable;
import com.grupp26.aquasim.model.IModelFacade;
import com.grupp26.aquasim.view.FishSelectionListener;
import com.grupp26.aquasim.view.IMainView;
import com.grupp26.aquasim.view.FoodSelectionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

import com.grupp26.aquasim.view.DecorationSelectionListener;

public class Controller implements IController {
    // offset to make it feel more resposive, placing it at the mouse instead of
    // down to the right
    private static final int MOUSE_OFFSET = 25;
    private final String NO_TYPE = "";

    private String selectedType = NO_TYPE;

    IModelFacade modelFacade;
    IMainView view;
    int mouseX;
    int mouseY;
    ActiveMode mouseMode = ActiveMode.NONE;
    ISoundObservable mediaPlayer;

    public Controller(IModelFacade modelFacade, IMainView view, ISoundObservable mediaPlayer) {
        this.modelFacade = modelFacade;
        this.view = view;
        this.mediaPlayer = mediaPlayer;
        initListeners();
    }

    // Controller reggar sig själv som lyssnare på addFish-knappen i view
    private void initListeners() {

        view.getAddFishButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mediaPlayer.notifyPlaySound("click");
                handleMouseState(ActiveMode.FISH_MENU);
            }
        });

        view.getRemoveFishButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                mediaPlayer.notifyPlaySound("click");
                modelFacade.removeFish();
            }
        });

        view.getAddFoodButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mediaPlayer.notifyPlaySound("click");
                if (mouseMode == ActiveMode.FOOD_MENU) {
                    handleMouseState(ActiveMode.NONE);
                } else {
                    handleMouseState(ActiveMode.FOOD_MENU);
                }
            }
        });

        view.addFoodMenuListener(new FoodSelectionListener() {
            @Override
            public void onFoodSelected(String foodType) {
                mediaPlayer.notifyPlaySound("click");
                // If already placing this food type, deactivate
                if (mouseMode == ActiveMode.PLACING_FOOD && selectedType.equals(foodType)) {
                    handleMouseState(ActiveMode.NONE);
                } else {
                    handleMouseState(ActiveMode.PLACING_FOOD, foodType);
                }
            }
        });

        view.addDecorationMenuListener(new DecorationSelectionListener() {
            @Override
            public void onDecorationSelected(String decorationType) {
                mediaPlayer.notifyPlaySound("click");
                if (mouseMode == ActiveMode.PLACING_DECORATION && selectedType.equals(decorationType)) {
                    handleMouseState(ActiveMode.NONE);
                } else {
                    handleMouseState(ActiveMode.PLACING_DECORATION, decorationType);
                }
            }
        });
        view.getDecorationButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mediaPlayer.notifyPlaySound("click");
                if (mouseMode == ActiveMode.DECORATION_MENU) {
                    handleMouseState(ActiveMode.NONE);
                } else {
                    handleMouseState(ActiveMode.DECORATION_MENU);
                }
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
                mediaPlayer.notifyPlaySound("click");
                if (mouseMode == ActiveMode.PLACING_FOOD) {
                    modelFacade.addFood(selectedType.toLowerCase(), mouseX, mouseY);
                } else if (mouseMode == ActiveMode.PLACING_FISH) {
                    modelFacade.addFish(selectedType.toLowerCase(), mouseX, mouseY);
                } else if (mouseMode == ActiveMode.PLACING_DECORATION) {
                    modelFacade.addDecoration(selectedType.toLowerCase(), mouseX, mouseY);
                }
            }
        });

        view.addFishMenuListener(new FishSelectionListener() {
            @Override
            public void onFishSelected(String fishType) {
                mediaPlayer.notifyPlaySound("click");
                // OM vi redan valt denna fisktyp, avaktivera menyn
                if (mouseMode == ActiveMode.PLACING_FISH && selectedType.equals(fishType)) {
                    handleMouseState(ActiveMode.NONE);
                } else {
                    handleMouseState(ActiveMode.PLACING_FISH, fishType);
                }
            }
        });
    }

    private void handleMouseState(ActiveMode mode) {
        handleMouseState(mode, NO_TYPE);
    }

    private void handleMouseState(ActiveMode mode, String type) {
        mouseMode = mode;
        if (mode == ActiveMode.PLACING_FISH || mode == ActiveMode.PLACING_FOOD
                || mode == ActiveMode.PLACING_DECORATION) {
            selectedType = type;
        } else {
            selectedType = NO_TYPE;
        }
        view.updateActiveButton(mode, type);
    }

}
