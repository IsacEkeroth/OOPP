package com.grupp26.aquasim.model;

import java.util.ArrayList;

public interface IAquarium {

    void addFish(IFish fish);

    void removeFish(IFish fish);

    boolean isValidPosition(Vec2<Integer> pos, Vec2<Integer> size);

    Vec2<Integer> getAquariumSize();

    void tick();

    AquariumState getState();

    ArrayList<IEdible> getFood();

    void addDecoration(IDecoration decoration);

    void removeLastFish();

    void addFood(IEdible edible);
}
