package com.grupp26.aquasim.model;

public class TickableDecoration extends Decoration implements ITickable {

    private final IAquarium aquarium;

    public TickableDecoration(IAquarium aquarium, Vec3<Integer> pos) {
        super(aquarium, pos);
        this.aquarium = aquarium;
    }

    @Override
    public void tick() {
        aquarium.setAlgaeLevel(aquarium.getAlgaeLevel() + 1);
    }

}
