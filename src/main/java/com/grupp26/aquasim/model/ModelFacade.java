package com.grupp26.aquasim.model;

import com.grupp26.aquasim.view.IObserver;

import java.util.ArrayList;

public class ModelFacade implements IModelFacade {
    private final IAquarium aquarium;
    private final FishFactory fishFactory;
    private AquariumState state;
    private ArrayList<IEntity> entities;
    private ArrayList<IObserver> observers = new ArrayList<>();

    // TODO -- Typerna är bara en placeholder för tillfället --
    // TODO -- Factory method borde sköta det istället? --
    private final String BG_TYPE = "BG";
    private final String FOOD_TYPE = "FOOD";
    private DecorationFactory decorationFactory;
    private FoodFactory foodFactory;

    private final ISimulationLoop simLoop;
    private final int SIMULATION_DELAY = 25; // milliseconds

    public ModelFacade(IAquarium aquarium, IObserver observer) {
        this.aquarium = aquarium;
        this.observers.add(observer);
        this.fishFactory = new FishFactory();
        this.decorationFactory = new DecorationFactory(aquarium);
        this.foodFactory = new FoodFactory(aquarium);
        School.reset(); // ensures singleton is reset when a new model is created
        this.simLoop = new SimulationLoop(SIMULATION_DELAY, this::tick);
    }

    @Override
    public void tick() {
        aquarium.tick();
        state = aquarium.getState();
        entities = new ArrayList<>();

        IEntity bgEntity = new Entity(
                new Vec3<Integer>(0, 0, 0),
                aquarium.getAquariumSize(),
                BG_TYPE,
                "null", true);
        entities.add(bgEntity);

        for (IFish fish : state.getFish()) {
            IEntity entity = new Entity(
                    fish.getPos(),
                    fish.getSize(),
                    fish.getType(),
                    fish.getFishID(), !isDirectionRight(fish.getDirection())); // inverted direction since our sprites
                                                                               // are now to the left, this should be
                                                                               // handled by the sprite manager in the
                                                                               // future
            entities.add(entity);
        }
        for (IDecoration deco : state.getDecorations()) {
            IEntity entity = new Entity(deco.getPos(),
                    new Vec2<Integer>(deco.getSize().getX(), deco.getSize().getY()), deco.getType(),
                    "null",
                    true);

            entities.add(entity);
        }
        for (IEdible food : state.getFood()) {
            IEntity entity = new Entity(food.getPos(), food.getSize(), FOOD_TYPE, "null", true);
            entities.add(entity);
        }
        notifyObservers();
    }

    public void addFish(String fishType, int posX, int posY) {
        Fish fish;

        switch (fishType.toLowerCase()) {
            case "goldfish":
                fish = fishFactory.createGoldfish(aquarium, Math.random() * 360);
                break;
            case "clownfish":
                fish = fishFactory.createClownfish(aquarium, Math.random() * 360);
                break;
            default: // om nått går fel så skapas bara en goldfish
                fish = fishFactory.createGoldfish(aquarium, Math.random() * 360);
                break;
        }
        fish.setPos(posX, posY, fish.getPos().getZ());
        aquarium.addFish(fish);
        notifyObservers();
    }

    private boolean isDirectionRight(double direction) {
        return Math.cos(direction) > 0;
    }

    @Override
    public ArrayList<IEntity> getEntities() {
        return new ArrayList<>(entities);
    }

    @Override
    public void removeFish() {
        // Temporary call to removeLastFish() --> Delete later
        aquarium.removeLastFish();
    }

    @Override
    public void addDecoration(String type, int x, int y) {
        IDecoration decoration = decorationFactory.createDecoration(type);
        decoration.setPos(x, y, decoration.getPos().getZ());
        aquarium.addDecoration(decoration);
    }

    @Override
    public void addFood(String type, int posX, int posY) {
        IEdible food = foodFactory.createFood(type);
        food.setPos(posX, posY, food.getPos().getZ());
        aquarium.addFood(food);
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
