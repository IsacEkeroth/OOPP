package com.grupp26.aquasim.controller;

import com.grupp26.aquasim.ISoundObservable;
import com.grupp26.aquasim.model.IModelFacade;
import com.grupp26.aquasim.view.FishSelectionListener;
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
    private final String NO_FISH_TYPE = "";

    private String selectedFishType = NO_FISH_TYPE;

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

    // TODO -- Pga knapparna ska släckas och lysa vid klick har det blivit en del
    // logik här, vi skulle kunna skita i det --
    // Controller reggar sig själv som lyssnare på addFish-knappen i view
    private void initListeners() {

        // Borde ändra namn nu kanske? Denna öppnar bara menyn
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
                if (mouseMode == ActiveMode.FOOD) {
                    handleMouseState(ActiveMode.NONE);
                } else {
                    handleMouseState(ActiveMode.FOOD);
                }
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
                mediaPlayer.notifyPlaySound("click");
                if (mouseMode == ActiveMode.FOOD) {
                    modelFacade.addFood("base", mouseX, mouseY);

                } else if (mouseMode == ActiveMode.PLACING_FISH) {
                    modelFacade.addFish(selectedFishType, mouseX, mouseY);
                }
            }
        });

        view.addFishMenuListener(new FishSelectionListener() {
            @Override
            public void onFishSelected(String fishType) {
                mediaPlayer.notifyPlaySound("click");
                // OM vi redan valt denna fisktyp, avaktivera menyn
                if (mouseMode == ActiveMode.PLACING_FISH && selectedFishType.equals(fishType)) {
                    handleMouseState(ActiveMode.NONE);
                } else {
                    // Annars, aktivera fiskmenyn
                    handleMouseState(ActiveMode.PLACING_FISH, fishType);
                }
            }
        });
    }

    private void handleMouseState(ActiveMode mode) {
        handleMouseState(mode, NO_FISH_TYPE);
    }

    private void handleMouseState(ActiveMode mode, String fishType) {
        mouseMode = mode;
        selectedFishType = fishType;

        view.updateActiveButton(mode, fishType);
    }

}
