package com.grupp26.aquasim.model;

public interface IFish {
    // comment = private attributes
    // has the following
    // int age;
    // int speed;
    // Vector<Integer> pos; // x, y, z
    // IAi ai;

    IAquarium getAquarium();

    int getAge();

    Vec2<Integer> getSize();

    int getSpeed();

    Vec3<Integer> getPos();

    void setPos(int x, int y, int z);

    void tick();
}
