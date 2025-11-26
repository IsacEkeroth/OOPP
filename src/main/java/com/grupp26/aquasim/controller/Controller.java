package com.grupp26.aquasim.controller;

import com.grupp26.aquasim.model.TmpModelFacade;
import com.grupp26.aquasim.view.MainView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class Controller implements IController {

    TmpModelFacade model;
    MainView view;

    public Controller(TmpModelFacade model, MainView view) {
        this.model = model;
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
                model.addFish();
            }
        });
    }










}
