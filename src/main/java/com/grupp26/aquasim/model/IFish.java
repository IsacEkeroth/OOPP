package com.grupp26.aquasim.model;

public interface IFish extends ITickable {
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

    String getFishID();

    boolean isAlive();

    double getDirection();

    String getType();

    void setLove(boolean love);

    boolean isInLove();
}