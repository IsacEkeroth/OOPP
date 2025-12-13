package com.grupp26.aquasim.model;

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