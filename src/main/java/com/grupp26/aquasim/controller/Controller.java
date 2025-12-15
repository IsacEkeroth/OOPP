package com.grupp26.aquasim.controller;

import com.grupp26.aquasim.ISoundObservable;
import com.grupp26.aquasim.model.IModelFacade;
import com.grupp26.aquasim.view.IMainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements IController {

    IModelFacade modelFacade;
    IMainView view;
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
                mediaPlayer.notifyPlaySound("click");
                modelFacade.addFish();
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
                modelFacade.addFood("base");
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

    }

}
