package com.grupp26.aquasim.model;

public interface IEdible {

    void setPos(Vec3<Integer> pos);

    Vec3<Integer> getPos();


    void setAmount(int amount);

    int getAmount();


    void eat(int amount);

    boolean isEaten();

}