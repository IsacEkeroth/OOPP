package com.grupp26.aquasim.model;

import com.grupp26.aquasim.view.IObserver;

import java.util.ArrayList;

public class ModelFacade implements IModelFacade {
    private final IAquarium aquarium;
    private AquariumState state;
    private ArrayList<IEntity> entities;
    private ArrayList<IObserver> observers = new ArrayList<>();
    private DecorationFactory decorationFactory;
    private FoodFactory foodFactory;

    public ModelFacade(IAquarium aquarium, IObserver observer) {
        this.aquarium = aquarium;
        this.observers.add(observer);
        this.decorationFactory = new DecorationFactory(aquarium);
        this.foodFactory = new FoodFactory(aquarium);
    }

    public void tick() {
        aquarium.tick();
        state = aquarium.getState();
        entities = new ArrayList<>();

        IEntity bgEntity = new Entity(new Vec3<Integer>(0, 0, 0),
                aquarium.getAquariumSize(),
                "images/akvarium1.jpg", true);
        entities.add(bgEntity);

        // store the imagepath in fish or new fishData class?
        for (IFish fish : state.getFish()) {
            boolean isFacingRight = isDirectionRight(fish.getDirection());
            IEntity entity = new Entity(fish.getPos(),
                    fish.getSize(),
                    "images/icon-grupp26nobg.png", isFacingRight); // all fish are smurfs
            entities.add(entity);
        }
        for (IDecoration deco : state.getDecorations()) {
            IEntity entity = new Entity(
                    deco.getPos(),
                    new Vec2<Integer>(deco.getSize().getX(), deco.getSize().getY()),
                    "images/veryGoodAnchor.png",
                    true); // all decorations are anchors
            entities.add(entity);
        }
        for (IEdible food : state.getFood()) {
            IEntity entity = new Entity(food.getPos(), food.getSize(), "images/Food.png", true);
            entities.add(entity);
        }
        notifyObservers();
    }

    private boolean isDirectionRight(double direction) {
        return Math.cos(direction) > 0;
    }

    public ArrayList<IEntity> getEntities() {
        return new ArrayList<>(entities);
    }

    // some kind of argument from controller to know which fish to add: enum,
    // String, int?
    public void addFish() {
        aquarium.addFish(new Fish(aquarium));
        notifyObservers();
    }

    @Override
    public void removeFish() {
        // Temporary call to removeLastFish() --> Delete later
        aquarium.removeLastFish();
        notifyObservers();
    }

    public void addDecoration(String type, int x, int y) {
        IDecoration decoration = decorationFactory.createDecoration(type);
        decoration.setPos(x, y, decoration.getPos().getZ());
        aquarium.addDecoration(decoration);
        notifyObservers();
    }

    public void addFood(String type) {
        aquarium.addFood(foodFactory.createFood(type));
        notifyObservers();
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
