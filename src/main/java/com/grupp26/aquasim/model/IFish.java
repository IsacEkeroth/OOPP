package com.grupp26.aquasim.model;

public interface IFish {

    IAquarium getAquarium();

    int getAge();

    void setHealth(int health);

    int getHunger();

    void setHunger(int hunger);

    int getBaseSpeed();

    void setBaseSpeed(int baseSpeed);

    Vec2<Integer> getSize();

    int getSpeed();

    Vec3<Integer> getPos();

    void setPos(int x, int y, int z);

    void tick();

}