package com.grupp26.aquasim.model;

public class Decoration implements IDecoration {

    private final IAquarium aquarium;

    private Vec2<Integer> size = new Vec2<Integer>(100, 100);

    private Vec3<Integer> pos;

    public Decoration(IAquarium aquarium, Vec3<Integer> pos) {

        this.aquarium = aquarium;

        this.pos = pos;

    }

    @Override
    public Vec2<Integer> getSize() {
        return new Vec2<Integer>(size.getX(), size.getY());
    }

    @Override
    public void setPos(int x, int y, int z) {
        if (aquarium.isValidPosition(new Vec2<>(x, y), size)) {
            pos.setX(x);
            pos.setY(y);
            pos.setZ(z);
        } else {
            throw new IllegalArgumentException("Invalid position for decoration");
        }
    }

    @Override
    public Vec3<Integer> getPos() {
        return pos;
    }

}
