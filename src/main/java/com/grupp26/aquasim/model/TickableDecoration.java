package com.grupp26.aquasim.model;

/**
 * Represents an interactive decoration that affects the environment with every tick.
 * <p>
 *     By implementing {@link ITickable}, this type of decoration can execute logic
 *     continuously during the simulation to influence the aquarium's state.
 * </p>
 */
public class TickableDecoration extends Decoration implements ITickable {
    private final String TYPE = "SEAWEED";
    private final IAquarium aquarium;

    public TickableDecoration(IAquarium aquarium, Vec3<Integer> pos) {
        super(aquarium, pos);
        this.aquarium = aquarium;
    }

    @Override
    public void tick() {
        aquarium.setAlgaeLevel(aquarium.getAlgaeLevel() + 1);
    }

    @Override
    public String getType() {
        return TYPE;
    }

}
