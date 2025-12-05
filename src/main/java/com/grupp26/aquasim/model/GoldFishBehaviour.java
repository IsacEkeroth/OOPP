package com.grupp26.aquasim.model;

public class GoldFishBehaviour implements IBehaviour {
    private IAquarium aquarium;

    private IFishState state;
    private IFishState passiveState;
    private IFishState hungerState;
    private int hungryAt;

    public GoldFishBehaviour(IFish fish, double initialDirection, int hungryAt) {
        this.hungryAt = hungryAt;
        this.aquarium = fish.getAquarium();

        this.passiveState = new PassiveState(this, fish, new SimpleMove(this.aquarium, initialDirection), aquarium);
        this.hungerState = new HungerState(this, fish, new TargetMove(this.aquarium, initialDirection), aquarium);
        this.state = this.passiveState;
    }

    public void setState(IFishState newState) {
        this.state = newState;
    }

    public IFishState getPassiveState() {
        return passiveState;
    }

    public IFishState getHungerState() {
        return hungerState;
    }

    public int getHungryAt() {
        return this.hungryAt;
    }

    public void update() {
        this.state.update();
    }

}