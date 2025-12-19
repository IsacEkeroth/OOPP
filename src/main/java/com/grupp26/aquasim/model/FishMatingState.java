package com.grupp26.aquasim.model;
/**
 * FishMatingState represents the state of a fish when it is mating.
 * <p>
 *     In this state, the fish will perform mating behaviors and eventually give birth to offspring.
 * </p>
 */
public class FishMatingState implements IFishState {
    private IFish fish;
    private IAquarium aquarium;
    private IFishBehaviour context;
    private FishFactory factory;
    private int matingCounter; // räkna ned från ett nummer, om de parat klart är den 0
    private int moveStage; // ha koll på hur vi rör oss under rörelsen
    
    public FishMatingState(IFishBehaviour context, IFish fish, IAquarium aquarium) {
        this.context = context;
        this.fish = fish;
        this.aquarium = aquarium;
        factory = new FishFactory(aquarium);
        matingCounter = 100;
        moveStage = 0;
    }

    private void giveBirth() {
        if (fish.canSpawnChild()) {
            if (fish.getType().equals("Goldfish")) {
                IFish fishA = factory.createGoldfish(0);
                fishA.setPos(this.fish.getPos().getX(), this.fish.getPos().getY(), this.fish.getPos().getZ());
                aquarium.addFishToBuffer(fishA);
                this.fish.setSpawnChild(false); // nollsätta
            }
            if (fish.getType().equals("Clownfish")) {
                IFish fishB = factory.createClownfish(0);
                fishB.setPos(this.fish.getPos().getX(), this.fish.getPos().getY(), this.fish.getPos().getZ());
                aquarium.addFishToBuffer(fishB);
                this.fish.setSpawnChild(false); // nollsätta
            }
        }
    }

    private IFishState checkState() {
        if (!fish.isAlive()) {
            return context.getDeathState();
        } else if (matingCounter != 0) {
            return this;
        } else {
            return context.getPassiveState();
        }
    }

    private void action() {
        moveStage += 1;
        matingCounter -= 1;
        if (moveStage % 2 == 0) {
            fish.setPos(fish.getPos().getX() + 10, fish.getPos().getY(), fish.getPos().getZ());
        } else if (moveStage % 2 == 1) {
            fish.setPos(fish.getPos().getX() - 10, fish.getPos().getY(), fish.getPos().getZ());
        }
    }

    public void update() {
        IFishState newstate = checkState();
        if (newstate.equals(this)) {
            action();
            if (matingCounter <= 0) {
                matingCounter = 0;
                giveBirth();
            }
        } else {
            matingCounter = 100;
            context.setState(newstate);
        }
    }

    @Override
    public double getDirection() {
        return 0;
    }

}
