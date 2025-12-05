package com.grupp26.aquasim.model;

import java.util.ArrayList;

public interface IAquarium {
    // comment = private attributes
    // List<IFish> fish;
    // Point aquariumSize = new Point(720, 540);

    void addFish(IFish fish);

    void removeFish(IFish fish);

    boolean isValidPosition(Vec2<Integer> pos, Vec2<Integer> size);

    Vec2<Integer> getAquariumSize();

    void tick();
    
    AquariumState getState();

    ArrayList<IEdible> getFood();
    
    void addDecoration(IDecoration decoration);

    // Temporary method --> Delete later
    void removeLastFish();
  
    void addFood(IEdible edible);
}
