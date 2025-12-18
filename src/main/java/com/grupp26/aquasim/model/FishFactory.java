package com.grupp26.aquasim.model;

/**
 * A factory responsible for creating different types of instances of fish.
 * <p>
 * The class centralises the configuration of different species of fish through defining
 * their physiological attributes and behaviours.
 * <br> This hides the complexity at instantiation and makes sure that all fish of a
 * given species is created with the correct base values.
 * </p>
 */
public class FishFactory {

    private final IFishTypeData goldfishData = new FishTypeData(
            "Goldfish",
            75,
            10,
            15,
            (fish,direction)->new FishBehaviour(fish,direction, 100));

    private final IFishTypeData clownfishData = new FishTypeData(
            "Clownfish",
            100,
            5,
            25,
            (fish,direction)->new FishBehaviour(fish,direction, 75));

    public Fish createGoldfish(IAquarium aquarium, double initialDirection) {
        return new Fish(aquarium, goldfishData, initialDirection);
    }

    public Fish createClownfish(IAquarium aquarium, double initialDirection) {
        return new Fish(aquarium, clownfishData, initialDirection);
    }

}