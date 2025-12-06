package com.grupp26.aquasim.model;

import com.grupp26.aquasim.view.IObserver;

import java.util.ArrayList;

public class ModelFacade implements IObservable {
    private final IAquarium aquarium;
    private AquariumState state;
    private ArrayList<IEntity> entities;
    private ArrayList<IObserver> observers = new ArrayList<>();

    public ModelFacade(IAquarium aquarium, IObserver observer) {
        this.aquarium = aquarium;
        this.observers.add(observer);
    }

    public void tick() {
        aquarium.tick();
        state = aquarium.getState();
        entities = new ArrayList<>();

        IEntity bgEntity = new Entity(new Vec3<Integer>(0, 0, 0),
                aquarium.getAquariumSize(),
                "images/akvarium1.jpg");
        entities.add(bgEntity);

        for (IFish fish : state.getFish()) {
            IEntity entity = new Entity(fish.getPos(),
                    fish.getSize(),
                    "images/icon-grupp26nobg.png");
            entities.add(entity);
        }
        for (IDecoration deco : state.getDecorations()) {
            IEntity entity = new Entity(deco.getPos(),
                    new Vec2<Integer>(deco.getSize(), deco.getSize()),
                    "images/veryGoodAnchor.png");
            entities.add(entity);
        }
        notifyObservers();
    }

    public ArrayList<IEntity> getEntities() {
        return new ArrayList<>(entities);
    }

    public void addFish() {
        aquarium.addFish(new Fish(aquarium));
        notifyObservers();
    }

    public void removeFish() {
        aquarium.removeLastFish();
        notifyObservers();
    }

    public void addDecoration() {
        aquarium.addDecoration(new Decoration());
    }

    public void feedFish() {
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
