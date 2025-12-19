package com.grupp26.aquasim.model;

public class FishPassiveState implements IFishState {
    private IFish fish;
    private IMovement passiveMove;
    private IFishBehaviour context;
    private IAquarium aquarium;

    public FishPassiveState(IFishBehaviour context, IFish fish, IMovement passiveMove, IAquarium aquarium) {
        this.context = context;
        this.fish = fish;
        this.passiveMove = passiveMove;
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

    private boolean areTherePartners() {
        for (IFish fish : aquarium.getinLoveFish(this.fish)) {
            if (fish.isAlive() && this.fish.isInLove()) {
                return true;
            }
        }
        return false;
    }

    private IFishState checkState() {
        if (!fish.isAlive()) {
            return context.getDeathState();

        } else if (areTherePartners()) {
            return context.getLoveSeekingState();
        } else if (this.fish.getHunger() >= context.getHungryAt() && aquarium.getFood() != null
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
            this.passiveMove.move(fish);
        }
        // check state, if no switch, continue swimming
    }

    @Override
    public double getDirection() {
        return passiveMove.getDirection();
    }
}
