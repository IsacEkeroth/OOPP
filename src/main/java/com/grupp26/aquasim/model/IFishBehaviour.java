package com.grupp26.aquasim.model;

public interface IFishBehaviour {
    int getHungryAt();
    // every fish needs hunger?

    void update();

    void setState(IFishState newState);

    IFishState getPassiveState();

    IFishState getHungerState();

    IFishState getLoveSeekingState();

    IFishState getMatingState();

    IFishState getDeathState();
    // state pattern

    double getDirection();
}
