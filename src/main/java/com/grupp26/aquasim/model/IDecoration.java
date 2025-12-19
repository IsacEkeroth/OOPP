package com.grupp26.aquasim.model;

public interface IDecoration {

    Vec2<Integer> getSize();

    void setPos(int x, int y, int z);

    Vec3<Integer> getPos();

    String getType();

}
