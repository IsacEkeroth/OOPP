package com.grupp26.aquasim.model;

public interface IFoodBehaviour {
    
    void update();

    void setState(IFoodState newState);
    IFoodState getSinkingState();
    IFoodState getStaleState();
    IFoodState getEatenState();
    //state pattern
}
