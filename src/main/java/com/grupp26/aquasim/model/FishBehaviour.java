package com.grupp26.aquasim.model;

public class FishBehaviour implements IFishBehaviour {
    private IAquarium aquarium;

    private IFishState state;
    private IFishState passiveState;
    private IFishState hungerState;
    private IFishState deathState;
    private IFishState loveSeekingState;
    private IFishState matingState;
    private int hungryAt;

    public FishBehaviour(IFish fish, double initialDirection, int hungryAt) {
        this.hungryAt = hungryAt;
        this.aquarium = fish.getAquarium();

        this.passiveState = new FishPassiveState(this, fish, new SimpleMove(this.aquarium, initialDirection), aquarium);
        this.hungerState = new FishHungerState(this, fish, new TargetMove(this.aquarium, initialDirection), aquarium);
        this.loveSeekingState = new FishLoveSeekingState(this, fish, new TargetMove(this.aquarium, initialDirection), aquarium);
        this.matingState = new FishMatingState(this, fish, aquarium);

        this.deathState = new FishDeathState(this, fish, aquarium);
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

    public IFishState getLoveSeekingState() {
        return loveSeekingState;
    }

    public IFishState getMatingState() {
        return matingState;
    }

    public int getHungryAt() {
        return this.hungryAt;
    }

    @Override
    public void update() {
        this.state.update();
        //System.out.println(state);
    }

    @Override
    public double getDirection() {
        return this.state.getDirection();
    }
}