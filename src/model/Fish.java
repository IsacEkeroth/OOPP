package model;

import java.util.Vector;

public class Fish implements IFish {

    // set by the aquarium itself?
    private final IAquarium aquarium;

    private int age;
    private int speed;
    private Vec3<Integer> pos;

    Fish(IAquarium aquarium) {
        this.aquarium = aquarium;
    }

    @Override
    public IAquarium getAquarium() {
        return aquarium;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public Vec3<Integer> getPos() {
        return pos;
    }

    @Override
    public void setPos(int x, int y, int z) {
        pos.setX(0);
        pos.setY(1);
        pos.setZ(2);
    }

    @Override
    public void tick() {

    }
}
