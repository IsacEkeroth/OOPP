package com.grupp26.aquasim.controller;

import com.grupp26.aquasim.model.IModelFacade;

import com.grupp26.aquasim.view.IMainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements IController {

    IModelFacade modelFacade;
    IMainView view;

    public Controller(IModelFacade modelFacade, IMainView view) {
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
            public void actionPerformed(ActionEvent e) { modelFacade.feedFish(); }
        });

    }

}
