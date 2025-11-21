package com.grupp26.aquasim;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.Timer;

import com.grupp26.aquasim.view.MainView;
import com.grupp26.aquasim.view.Entity;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;

public class App {

    private static final int windowWidth = 1280;
    private static final int windowHeight = 720;

    public static void start() {
        MainView view = new MainView(windowWidth, windowHeight);

        // this should go in the model facade but is here for now
        Entity bgEntity = new Entity(new Point(0, 0), 0, new Point(windowWidth, windowHeight),
                getImage("images/akvarium1.jpg"));
        Entity f1 = new Entity(new Point(0, 0), 1, new Point(50, 50),
                getImage("images/icon-grupp26.png"));
        Entity f2 = new Entity(new Point(200, 200), 1, new Point(50, 50),
                getImage("images/icon-grupp26.png"));

        view.addEntity(bgEntity);
        view.addEntity(f1);
        view.addEntity(f2);

        view.repaint();

        Timer timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Point p = f1.getPos();
                p.x++;
                p.x++;
                p.y++;
                f1.setPos(p);

                view.repaint();
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
