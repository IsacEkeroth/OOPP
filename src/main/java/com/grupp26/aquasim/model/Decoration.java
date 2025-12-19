package com.grupp26.aquasim.model;

/**
 * Represents a decoration object within the aquarium.
 */
public class Decoration implements IDecoration {
    private final String TYPE = "ANCHOR";

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
        Vec2<Integer> clampedPos = aquarium.clampPosition(new Vec2<Integer>(x, y), size);
        pos.setX(clampedPos.getX());
        pos.setY(clampedPos.getY());
        pos.setZ(z);
    }

    @Override
    public Vec3<Integer> getPos() {
        return pos;
    }

    @Override
    public String getType() {
        return TYPE;
    }

}
