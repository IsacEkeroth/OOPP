package com.grupp26.aquasim.model;

public interface IFish extends ITickable {
    // comment = private attributes
    // has the following
    // int age;
    // int speed;
    // Vector<Integer> pos; // x, y, z
    // IAi ai;

    IAquarium getAquarium();

    int getAge();

    void setHealth(int health);

    int getHunger();

    void setHunger(int hunger);

    int getBaseSpeed();

    Vec2<Integer> getSize();

    int getSpeed();

    Vec3<Integer> getPos();

    int getBitingPower();

    void setPos(int x, int y, int z);

    boolean isAlive();

    double getDirection();

}