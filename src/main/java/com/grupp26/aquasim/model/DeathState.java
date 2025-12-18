package com.grupp26.aquasim.model;

/**
 * Represents the terminal state of a fish after its health has been depleted.
 * <p>
 *     In this state, the fish is considered deceased and all active behaviors,
 *    such as movement and hunger-tracking, are disabled. <br>
 *    This state serves as a final point in the fish's lifecycle where no further
 *    transitions are possible.
 * </p>
 */
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
