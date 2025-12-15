package com.grupp26.aquasim.model;

public class DeathState implements IFishState {
    private IFish fish;
    private IAquarium aquarium;
    private IFishBehaviour context;

    public DeathState(IFishBehaviour context, IFish fish, IAquarium aquarium) {
        this.context = context;
        this.fish = fish;
        this.aquarium = aquarium;
        // variables for further addition (fish floats up when dead?)
    }

    public void update() {
        // no further action, fish can (currently) not be raised from the dead
    }

    @Override
    public double getDirection() {
        return 0;
    }

}
