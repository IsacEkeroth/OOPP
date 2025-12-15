package com.grupp26.aquasim.controller;

import com.grupp26.aquasim.ISoundObservable;
import com.grupp26.aquasim.model.IModelFacade;
import com.grupp26.aquasim.view.IMainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

public class Controller implements IController {
    // offset to make it feel more resposive, placing it at the mouse instead of
    // down to the right
    private static final int MOUSE_OFFSET = 25;

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

        // Anonym ActionListener
        view.getAddFishButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleMouseState(ActiveMode.FISH);
                mediaPlayer.notifyPlaySound("click");
                modelFacade.addFish(mouseX, mouseY);
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

                handleMouseState(ActiveMode.FOOD);
                mediaPlayer.notifyPlaySound("click");
                modelFacade.addFood("base", mouseX, mouseY);
            }
        });

        view.getDecorationButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // this should get the position from the mouse on a click later
                modelFacade.addDecoration("anchor", 500, 500);
                // adds two just to show both
                modelFacade.addDecoration("seaweed", 300, 500);
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
                if (mouseMode == ActiveMode.FOOD) {
                    modelFacade.addFood("base", mouseX, mouseY);
                } else if (mouseMode == ActiveMode.FISH) {
                    modelFacade.addFish(mouseX, mouseY);
                }
            }
        });
    }

    private void handleMouseState(ActiveMode mode) {
        if (mouseMode == mode) {
            mouseMode = ActiveMode.NONE;
            view.updateActiveButton(ActiveMode.NONE);
            return;
        }

        mouseMode = mode;
        view.updateActiveButton(mode);
    }

}
