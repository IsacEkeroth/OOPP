package com.grupp26.aquasim;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.Timer;

import com.grupp26.aquasim.model.Aquarium;
import com.grupp26.aquasim.model.ModelFacade;
import com.grupp26.aquasim.view.HelloFX;
import com.grupp26.aquasim.view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;

public class App {

    private static final int windowWidth = 1280;
    private static final int windowHeight = 720;

    public static void start() {

        // MainView view = new MainView(windowWidth, windowHeight);
        Aquarium model = new Aquarium();
        ModelFacade facade = new ModelFacade(model);

        HelloFX.setAquarium(facade);
        HelloFX.main(new String[0]);
        System.out.println("start asdas");

        // facade.addFish(); //Unitialized values in Fish causes runtime error

        Timer timer = new Timer(25, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                facade.tick();
            }
        });
        timer.start();

    }

    private static BufferedImage getImage(String path) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(App.class.getClassLoader().getResourceAsStream(path));
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
            img = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB); // fallback
        }
        return img;
    }

}
