package model;

import java.util.Vector;

public class Fish implements IFish {

    // set by the aquarium itself?
    private final IAquarium aquarium;

    private int age;
    private int size;
    private int speed;
    private Vector<Integer> pos;

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

    public int getSize() { return size;}

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public Vector<Integer> getPos() {
        return pos;
    }

    @Override
    public void setPos(int x, int y, int z) {
        pos.set(0, x);
        pos.set(1, y);
        pos.set(2, z);
    }

    @Override
    public void tick() {

    }
}
