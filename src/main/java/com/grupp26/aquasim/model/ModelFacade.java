package com.grupp26.aquasim.model;

import com.grupp26.aquasim.view.IObserver;

import java.util.ArrayList;

public class ModelFacade implements IObservable {
    private final IAquarium aquarium;
    private AquariumState state;
    private ArrayList <IEntity> entities;
    private ArrayList <IObserver> observers = new ArrayList<>();

    // TODO     -- Typerna är bara en placeholder för tillfället --
    // TODO     -- Factory method borde sköta det istället? --
    private final String FISH_TYPE = "FISH";
    private final String BG_TYPE = "BG";
    private final String DECOR_TYPE = "DECOR";

    public ModelFacade(IAquarium aquarium, IObserver observer) {
        this.aquarium = aquarium;
        this.observers.add(observer);
    }
    
    public void tick() {
        aquarium.tick();
        state = aquarium.getState();
        entities = new ArrayList<>();
        
        IEntity bgEntity = new Entity(
                new Vec3<Integer>(0, 0, 0),
                aquarium.getAquariumSize(),
                BG_TYPE,
                "null");
        entities.add(bgEntity);

        for(IFish fish : state.getFish()) {
            IEntity entity = new Entity(
                    fish.getPos(),
                    fish.getSize(),
                    FISH_TYPE,
                    fish.getFishID());
            entities.add(entity);
        }
        for(IDecoration deco : state.getDecorations()) {
            IEntity entity = new Entity(
                    deco.getPos(),
                    new Vec2<Integer>(deco.getSize(), deco.getSize()), // fix dec.getSize to return Vec2
                    DECOR_TYPE,
                    "null");
            entities.add(entity);
        }
        notifyObservers();
    }
    
    public ArrayList<IEntity> getEntities() {
        return new ArrayList<>(entities);
    }
    
    // some kind of argument from controller to know which fish to add: enum, String, int?
    // TODO     -- Lägg till fler knappar/menyval för att välja en specifik fisk --
    public void addFish() {
        aquarium.addFish(new Fish(aquarium));
        notifyObservers();
    }

    public void removeFish() {
        // Temporary call to removeLastFish() --> Delete later
        aquarium.removeLastFish();
        notifyObservers();
    }

    public void addDecoration() {
        aquarium.addDecoration(new Decoration(aquarium, new Vec3<>(0,0,0)));
    }

    public void feedFish() { }
    
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
