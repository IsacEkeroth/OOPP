package com.grupp26.aquasim.controller;

import com.grupp26.aquasim.model.ModelFacade;

import com.grupp26.aquasim.view.MainView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements IController {

    ModelFacade modelFacade;
    MainView view;

    public Controller(ModelFacade modelFacade, MainView view) {
        this.modelFacade = modelFacade;
        this.view = view;
        initListeners();
    }

    // Controller reggar sig själv som lyssnare på addFish-knappen i view
    private void initListeners() {

        // Anonym ActionListener
        view.getAddFishButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modelFacade.addFish();
            }
        });

        view.getRemoveFishButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modelFacade.removeFish();
            }
        });

        view.getFeedFishButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modelFacade.feedFish();
            }
        });

        view.getDecorationButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // this should get the position from the mouse on a click later
                modelFacade.addDecoration("anchor", 500, 500);
            }
        });

    }

}
