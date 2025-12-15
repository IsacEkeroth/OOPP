package com.grupp26.aquasim.model;

public interface IMovement {
    void move(IFish fish);
    double getDirection();
    void setDirection(double direction);
}