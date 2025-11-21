package model;

import java.util.Vector;

public interface IFish {
    // comment = private attributes
    // has the following
    // int age;
    // int speed;
    // Vector<Integer> pos; // x, y,     z
    // IAi ai;

    IAquarium getAquarium();

    int getAge();

    int getSpeed();

    Vec3<Integer> getPos();

    void setPos(int x, int y, int z);

    void tick();
}
