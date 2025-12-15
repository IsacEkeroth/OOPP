package com.grupp26.aquasim.model;

import java.util.ArrayList;

public interface IModelFacade extends IObservable {
    void tick();
    
    ArrayList<IEntity> getEntities();
    
    void addFish();
    
    void removeFish();
    
    void addDecoration();
    
    void feedFish();
}
