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
    
    int getTemperature();
    
    void setTemperature(int temp);
    
    int getAlgaeLevel();
    
    void setAlgaeLevel(int level);
    
    int getO2Conc();
    
    void setO2Conc(int conc);
    
    int getSalinity();
    
    void setSalinity(int salinity);
    
    int getPHLevel();
    
    void setPHLevel(int level);
    
    AquariumState getState();

    void tick();

    ArrayList<IEdible> getFood();
    
    void addDecoration(IDecoration decoration);

    // Temporary method --> Delete later
    void removeLastFish();
  
    void addFood(IEdible edible);
}
