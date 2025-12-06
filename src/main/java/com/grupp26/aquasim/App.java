package com.grupp26.aquasim;

import javax.swing.Timer;

import com.grupp26.aquasim.model.Aquarium;
import com.grupp26.aquasim.model.ModelFacade;
import com.grupp26.aquasim.view.MainView;
import com.grupp26.aquasim.controller.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {

    private static final int windowWidth = 1280;
    private static final int windowHeight = 720;

    public static void start() {
        MainView view = new MainView(windowWidth, windowHeight);
        Aquarium aquarium = new Aquarium();
        ModelFacade facade = new ModelFacade(aquarium, view);
        view.setFacade(facade);
        Controller controller = new Controller(facade, view);

        Timer timer = new Timer(25, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                facade.tick();
            }
        });
        timer.start();

    }
}
