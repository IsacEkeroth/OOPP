package com.grupp26.aquasim.model;

/**
 * Handles the logic for a fish behaviour and movement pattern through State Design Pattern.
 * <p>
 * The class functions as context in a State Design Pattern and delegates the decisions
 * to different {@link IFishState}-implementations (passive, hunger, death).
 * It is responsible for initiating the different states and provides a uniform
 * method, {@link #update()}, for updating the fish state.
 */
public class FishBehaviour implements IFishBehaviour {
    private IAquarium aquarium;

    private IFishState state;
    private IFishState passiveState;
    private IFishState hungerState;
    private IFishState deathState;
    private int hungryAt;

    public FishBehaviour(IFish fish, double initialDirection, int hungryAt) {
        this.hungryAt = hungryAt;
        this.aquarium = fish.getAquarium();

        this.passiveState = new PassiveState(this, fish, new SimpleMove(this.aquarium, initialDirection), aquarium);
        this.hungerState = new HungerState(this, fish, new TargetMove(this.aquarium, initialDirection), aquarium);
        this.deathState = new DeathState(this, fish, aquarium);
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

    public IFishState getDeathState() {
        return deathState;
    }

    public int getHungryAt() {
        return this.hungryAt;
    }

    public void update() {
        this.state.update();
    }

    public double getDirection() {
        return this.state.getDirection();
    }
}