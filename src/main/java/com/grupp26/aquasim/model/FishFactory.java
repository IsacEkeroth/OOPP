package com.grupp26.aquasim.model;

public class FishFactory {
    private IAquarium aquarium;

    FishFactory(IAquarium aquarium) {
        this.aquarium = aquarium;
    }

    private final IFishTypeData goldfishData = new FishTypeData(
            "Goldfish",
            750,
            5,
            15,
            (fish, direction) -> new FishBehaviour(fish, direction, 100));

    private final IFishTypeData clownfishData = new FishTypeData(
            "Clownfish",
            1000,
            6,
            25,
            (fish, direction) -> new FishBehaviour(fish, direction, 75));

    public Fish createGoldfish(double initialDirection) {
        return new Fish(aquarium, goldfishData, initialDirection);
    }

    public Fish createClownfish(double initialDirection) {
        return new Fish(aquarium, clownfishData, initialDirection);
    }

}