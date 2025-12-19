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
    private IFishState loveSeekingState;
    private IFishState matingState;
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
        this.passiveState = new FishPassiveState(this, fish, passiveMovement, aquarium);
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