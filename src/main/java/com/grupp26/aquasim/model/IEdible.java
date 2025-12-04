package com.grupp26.aquasim.model;

public interface IEdible {

    void setPos(int x, int y, int z);

    Vec3<Integer> getPos();


    void setAmount(int amount);

    int getAmount();


    void eat(int amount);

    boolean isLoveFood();

    boolean isEaten();

}