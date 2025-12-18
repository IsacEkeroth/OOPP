package com.grupp26.aquasim.model;

public class FishBehaviour implements IFishBehaviour {
    private IAquarium aquarium;

    private IFishState state;
    private IFishState passiveState;
    private IFishState hungerState;
    private IFishState deathState;
    private IMovement passiveMovement; // varies based on fish type
    private int hungryAt;

    public FishBehaviour(IFish fish, double initialDirection, int hungryAt) {
        this.hungryAt = hungryAt;
        this.aquarium = fish.getAquarium();
        
        if (fish.getType().equals("Goldfish")) {
            passiveMovement = new SchoolMove(aquarium, initialDirection);
            School.getInstance().addMember(fish); // unsure if this is the best place to add fish to school
        } else {
            passiveMovement = new SimpleMove(aquarium, initialDirection);
        }
        this.passiveState = new PassiveState(this, fish, passiveMovement, aquarium);
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