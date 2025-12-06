package com.grupp26.aquasim.model;

public interface IBehaviour {
    int getHungryAt();

    void update();

    void setState(IFishState newState);

    IFishState getPassiveState();

    IFishState getHungerState();
}
