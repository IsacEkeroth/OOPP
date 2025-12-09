package com.grupp26.aquasim.model;

public class DeathState implements IFishState {
    private IFish fish;
    private IAquarium aquarium;
    private IFishBehaviour context;

    public DeathState(IFishBehaviour context, IFish fish, IAquarium aquarium){
        this.context = context;
        this.fish = fish;
        this.aquarium = aquarium;
    }

    public void update(){
        fish.setHealth(0); //fish.tick() kills it
    }
}
