package com.grupp26.aquasim.model;

public interface IFishTypeData {

    int getMaxHealth();
    int getBaseSpeed();
    int getBitingPower();

    String getSpeciesName();
    IFishBehaviour createBehaviour(Fish fish, double initialDirection);
}