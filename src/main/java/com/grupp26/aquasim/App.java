package com.grupp26.aquasim;

import javax.swing.Timer;

import com.grupp26.aquasim.controller.IController;
import com.grupp26.aquasim.model.Aquarium;
import com.grupp26.aquasim.model.IAquarium;
import com.grupp26.aquasim.model.IModelFacade;
import com.grupp26.aquasim.model.ModelFacade;
import com.grupp26.aquasim.view.IMainView;
import com.grupp26.aquasim.view.MainView;
import com.grupp26.aquasim.controller.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {

    private static final int windowWidth = 1280;
    private static final int windowHeight = 720;

    public static void start() {
        IMainView view = new MainView(windowWidth, windowHeight);
        IAquarium aquarium = new Aquarium();
        IModelFacade facade = new ModelFacade(aquarium, view);
        view.setFacade(facade);
        IController controller = new Controller(facade, view); // IController useless?
        

        Timer timer = new Timer(25, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                facade.tick();
            }
        });
        timer.start();

    }
}
