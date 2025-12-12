package com.grupp26.aquasim.model;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class FishFactory {
    private final Map<String, Supplier<Fish>> registry = new HashMap<>();

    public FishFactory(IAquarium aquarium) {
        int centerX = aquarium.getAquariumSize().getX() / 2;
        int centerY = aquarium.getAquariumSize().getY() / 2;
        int depth = 1;

        // Register fish types
        register("goldfish", () -> {
            Fish fish = new Fish(aquarium);
            fish.setHunger(50);
            fish.setHealth(100);
            fish.setBaseSpeed(10);
            fish.setPos(centerX, centerY, depth);
            return fish;
        });
    }

    public void register(String type, Supplier<Fish> fishConstructor) {
        registry.put(type, fishConstructor);
    }

    public Fish createFish(String type) {
        Supplier<Fish> fishConstructor = registry.get(type);

        if (fishConstructor == null) {
            throw new IllegalArgumentException("Unknown fish type: " + type);
        }
        return fishConstructor.get();
    }
}