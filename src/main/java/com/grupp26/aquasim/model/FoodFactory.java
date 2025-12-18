package com.grupp26.aquasim.model;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * A factory-class that is responsible for creating and handling different types of food objects.
 * <p>
 *     The class implements a registry-based Factory Pattern that centralizes the logic for
 *     instantiation of food. <br>
 *     By using {@link Supplier}-functions it decouples the creation logic from the rest of the
 *     model, enabling easy expansion of new food types and other {@link IEdible} elements.
 *
 * </p>
 */
public class FoodFactory {
    private final Map<String, Supplier<IEdible>> registry = new HashMap<>();

    public FoodFactory(IAquarium aquarium) {
        int centerX = aquarium.getAquariumSize().getX() / 2;
        int centerY = 50;
        int depth = 1;

        // Register register food types
        register("base", () -> new Food(new Vec3<>(centerX, centerY, depth), 50, aquarium));
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
