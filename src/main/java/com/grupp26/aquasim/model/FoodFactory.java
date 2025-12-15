package com.grupp26.aquasim.model;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class FoodFactory {
    private final Map<String, Supplier<IEdible>> registry = new HashMap<>();

    public FoodFactory(IAquarium aquarium) {
        int centerX = aquarium.getAquariumSize().getX() / 2;
        int centerY = 50;
        int centerZ = 1;

        // Register register food types
        register("base", () -> new Food(new Vec3<>(centerX, centerY, centerZ), 50, aquarium));
    }

    public void register(String type, Supplier<IEdible> FoodConstructor) {
        registry.put(type, FoodConstructor);
    }

    public IEdible createFood(String type) {
        Supplier<IEdible> FoodConstructor = registry.get(type);

        if (FoodConstructor == null) {
            throw new IllegalArgumentException("Unknown food type: " + type);
        }
        return FoodConstructor.get();
    }

}
