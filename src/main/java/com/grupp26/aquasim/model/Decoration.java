package com.grupp26.aquasim.model;

public class Decoration implements IDecoration {

    private int size;

    private Vec3<Integer> pos;

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
