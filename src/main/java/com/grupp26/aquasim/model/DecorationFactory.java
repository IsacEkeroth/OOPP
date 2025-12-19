package com.grupp26.aquasim.model;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * A factory class responsible for creating and managing different types of decorations.
 * <p>
 * This class implements a registry-based Factory Pattern, allowing for dynamic instantiation of
 * decorative elements.
 * <p>
 * By using {@link Supplier} functions, it decouples the creation logic from
 * the rest of the model, enabling easy expansion of new decoration types like static or animated
 * (tickable) elements.
 * </p>
 */

public class DecorationFactory {
    private final Map<String, Supplier<IDecoration>> registry = new HashMap<>();

    public DecorationFactory(IAquarium aquarium) {

        int depth = 0;

        register("seaweed", () -> new TickableDecoration(aquarium, new Vec3<>(0, 0, depth)));

        register("anchor",
                () -> new Decoration(aquarium, new Vec3<>(0, 0, depth)));
    }

    public void register(String type, Supplier<IDecoration> decorationConstructor) {
        registry.put(type, decorationConstructor);
    }

    public IDecoration createDecoration(String type) {
        Supplier<IDecoration> decorationConstructor = registry.get(type);
        if (decorationConstructor == null) {
            throw new IllegalArgumentException("Unknown decoration type: " + type);
        }
        return decorationConstructor.get();
    }

}
