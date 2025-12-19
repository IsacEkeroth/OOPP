package com.grupp26.aquasim.model;

/**
 * Handles the logic for a fish behaviour and movement pattern through State
 * Design Pattern.
 * <p>
 * The class functions as context in a State Design Pattern and delegates the
 * decisions
 * to different {@link IFishState}-implementations (passive, hunger, death).
 * It is responsible for initiating the different states and provides a uniform
 * method, {@link #update()}, for updating the fish state.
 */
public class FishBehaviour implements IFishBehaviour {
    private final IAquarium aquarium;
    private IFishState state;
    private final IFishState passiveState;
    private final IFishState hungerState;
    private final IFishState deathState;
    private final IFishState loveSeekingState;
    private final IFishState matingState;
    private final IMovement passiveMovement;
    private final int hungryAt;

    public FishBehaviour(IFish fish, double initialDirection, int hungryAt) {
        this.hungryAt = hungryAt;
        this.aquarium = fish.getAquarium();

        if (fish.getType().equals("Goldfish")) {
            this.passiveMovement = new SchoolMove(aquarium, initialDirection);
            School.getInstance().addMember(fish);
        } else {
            this.passiveMovement = new SimpleMove(aquarium, initialDirection);
        }
        this.passiveState = new FishPassiveState(this, fish, passiveMovement, aquarium);
        this.hungerState = new FishHungerState(this, fish, new TargetMove(this.aquarium, initialDirection), aquarium);
        this.loveSeekingState = new FishLoveSeekingState(this, fish, new TargetMove(this.aquarium, initialDirection),
                aquarium);
        this.matingState = new FishMatingState(this, fish, aquarium);
        this.deathState = new FishDeathState(this, fish, aquarium);
        this.state = this.passiveState;
    }

    @Override
    public void setState(IFishState newState) {
        this.state = newState;
    }

    @Override
    public IFishState getPassiveState() {
        return passiveState;
    }

    @Override
    public IFishState getHungerState() {
        return hungerState;
    }

    @Override
    public IFishState getDeathState() {
        return deathState;
    }

    @Override
    public IFishState getLoveSeekingState() {
        return loveSeekingState;
    }

    @Override
    public IFishState getMatingState() {
        return matingState;
    }

    @Override
    public int getHungryAt() {
        return this.hungryAt;
    }

    @Override
    public void update() {
        this.state.update();
    }

    @Override
    public double getDirection() {
        return this.state.getDirection();
    }
}