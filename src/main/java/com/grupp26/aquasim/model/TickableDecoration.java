package com.grupp26.aquasim.model;

public class TickableDecoration implements IDecoration, ITickable {

    private final IAquarium aquarium;

    private Vec2<Integer> size = new Vec2<Integer>(100, 100);

    private Vec3<Integer> pos;

    public TickableDecoration(IAquarium aquarium, Vec3<Integer> pos) {

        this.aquarium = aquarium;

        this.pos = pos;

    }

    public Vec2<Integer> getSize() {
        return new Vec2<Integer>(size.getX(), size.getY());
    }

    public void setPos(int x, int y, int z) {
        pos.setX(x);
        pos.setY(y);
        pos.setZ(z);
    }

    public Vec3<Integer> getPos() {
        return pos;
    }

    public void tick() {
        aquarium.setAlgaeLevel(aquarium.getAlgaeLevel() + 1);
    }

}
