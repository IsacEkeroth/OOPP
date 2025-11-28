package com.grupp26.aquasim.controller;

import com.grupp26.aquasim.model.TmpModelFacade;
import com.grupp26.aquasim.view.MainView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class Controller implements IController {

    TmpModelFacade modelFacade;
    MainView view;

    public Controller(TmpModelFacade modelFacade, MainView view) {
        this.modelFacade = modelFacade;
        this.view = view;
        initListeners();
    }


    // Controller reggar sig själv som lyssnare på addFish-knappen i view
    private void initListeners() {

        // Anonym ActionListener
        // Lyssnar på addFish-knappen i view,
        // säger till ModelFacade att köra addFIsh
        view.getAddFish().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modelFacade.addFish();
            }
        });

        /* andra sättet man kunde skriva på ifall Controller implements ActionListener
        view.getAddFish().addActionListener(this);
         */
    }

    /* andra sättet man kunde skriva på ifall Controller implements ActionListener
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getAddFish()) model.addFish();
    }
     */








}
