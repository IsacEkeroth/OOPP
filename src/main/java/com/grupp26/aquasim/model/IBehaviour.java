package com.grupp26.aquasim.model;

public interface IBehaviour {
    int getHungryAt();
    //every fish needs hunger?
    
    void update();

    void setState(IFishState newState);
    IFishState getPassiveState();
    IFishState getHungerState();
    //state pattern
}
