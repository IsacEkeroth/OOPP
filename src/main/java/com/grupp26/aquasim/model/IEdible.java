package com.grupp26.aquasim.model;

public interface IEdible extends ITickable {

    void setPos(int x, int y, int z);

    Vec3<Integer> getPos();

    void setAmount(int nutritionValue);

    int getAmount();

    Vec2<Integer> getSize();

    void eatenBy(IFish fish);

    boolean isEaten();

    String getType();

}