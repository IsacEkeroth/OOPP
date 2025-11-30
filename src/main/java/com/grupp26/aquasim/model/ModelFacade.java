package com.grupp26.aquasim.model;

import com.grupp26.aquasim.App;
import com.grupp26.aquasim.view.Entity;
import com.grupp26.aquasim.view.IObserver;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

// TODO:
// make observable with view as observer
// when tick done, notify observers (view)
// view calls getEntities to then repaint

// Separate BufferedImage from Entity,
// add it in another class renderedEntity in view

public class ModelFacade implements IObservable {
    private final IAquarium aquarium;
    private AquariumState state;
    private ArrayList <Entity> entities;
    private ArrayList <IObserver> observers = new ArrayList<>();
    
    public ModelFacade(IAquarium aquarium, IObserver observer) {
        this.aquarium = aquarium;
        this.observers.add(observer);
    }
    
    public void tick() {
        aquarium.tick();
        state = aquarium.getState();
        entities = new ArrayList<>();
        
        Entity bgEntity = new Entity(new Point(0, 0),
                0,
                new Point(aquarium.getAquariumSize().getX(), aquarium.getAquariumSize().getY()),
                getImage("images/akvarium1.jpg"));
        entities.add(bgEntity);
        
        // fish.getSize() currently returns int, should be a vec2
        // store the imagepath in fish or new fishData class?
        for(IFish fish : state.getFish()) {
            Entity entity = new Entity(new Point(fish.getPos().getX(), fish.getPos().getY()),
                    fish.getPos().getZ(),
                    new Point(fish.getSize().getX(), fish.getSize().getY()),
                    //getImage("images/icon-grupp26.png")); // all fish are smurfs
                    getImage("images/veryGoodAnchor.png"));
            entities.add(entity);
        }
        for(IDecoration deco : state.getDecorations()) {
            Entity entity = new Entity(new Point(deco.getPos().getX(), deco.getPos().getY()),
                    deco.getPos().getZ(),
                    new Point(deco.getSize(), deco.getSize()),
                    getImage("images/veryGoodAnchor.png")); // all decorations are anchors
            entities.add(entity);
        }
        notifyObservers();
    }
    
    public ArrayList<Entity> getEntities() {
        return new ArrayList<>(entities);
    }
    
    // some kind of argument from controller to know which fish to add: enum, String, int?
    public void addFish() {
        aquarium.addFish(new Fish(aquarium));
    }
    public void addDecoration() { aquarium.addDecoration(new Decoration()); }
    
    
    private static final HashMap<String, BufferedImage> cache = new HashMap<>();
    private static BufferedImage getImage(String path) {
        if (cache.containsKey(path)) {
            return cache.get(path);
        }
        BufferedImage img = null;
        try {
            img = ImageIO.read(App.class.getClassLoader().getResourceAsStream(path));
            if (img != null) {
                cache.put(path, img);
            }
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
            img = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB); // fallback
        }
        return img;
    }
    
    @Override
    public void addObserver(IObserver observer) {
        observers.add(observer);
    }
    
    @Override
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }
    
    @Override
    public void notifyObservers() {
        for (IObserver observer : observers) {
            observer.update();
        }
    }
}
