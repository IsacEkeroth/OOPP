package com.grupp26.aquasim.model;

/**
 * Handles the fish behaviour when the fish is in a passive state.
 * <p>
 *     In this state the fish moves according to a standard-pattern via {@link SimpleMove},
 *     without actively searching for food. <br>
 *     The class consistently monitors the hunger-level of the fish and the environmental resources
 *     to determine when a transition to a different state should happen.
 * </p>
 */
public class PassiveState implements IFishState {
    private IFish fish;
    private SimpleMove simplemove;
    private IFishBehaviour context;
    private IAquarium aquarium;

    public PassiveState(IFishBehaviour context, IFish fish, SimpleMove simplemove, IAquarium aquarium) {
        this.context = context;
        this.fish = fish;
        this.simplemove = simplemove;
        this.aquarium = aquarium;
    }

    private boolean isThereFood() {
        for (IEdible edible : aquarium.getFood()) {
            if (!edible.isEaten()) {
                return true;
            }
        }
        return false;
    }

    private IFishState checkState() {
        if (!fish.isAlive()) {
            return context.getDeathState();
        } else if (this.fish.getHunger() > context.getHungryAt() && aquarium.getFood() != null
                && !aquarium.getFood().isEmpty() && isThereFood()) {
            return context.getHungerState();
            // if there is food in the aquarium and you are hungry, enter hungry mode
        } else {
            return context.getPassiveState();
        }
    }

    @Override
    public void update() {
        IFishState newState = checkState();
        if (!newState.equals(this)) {
            context.setState(newState);
        } else {
            this.simplemove.move(fish);
        }
        // check state, if no switch, continue swimming
    }

    public double getDirection() {
        return simplemove.getDirection();
    }
}
