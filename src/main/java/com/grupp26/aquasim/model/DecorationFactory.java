package com.grupp26.aquasim.model;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

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
