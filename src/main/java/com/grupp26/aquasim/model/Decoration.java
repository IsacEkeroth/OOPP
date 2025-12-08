package com.grupp26.aquasim.model;

public class Decoration implements IDecoration {

    private final IAquarium aquarium;

    private int size;

    private Vec3<Integer> pos;

    public Decoration(IAquarium aquarium, Vec3<Integer> pos) {

        this.aquarium = aquarium;

        this.pos = pos;

    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setPos(int x, int y, int z) {
        pos.setX(x);
        pos.setY(y);
        pos.setZ(z);
    }

    @Override
    public Vec3<Integer> getPos() {
        return pos;
    }

}
