package com.grupp26.aquasim.model;

import java.util.Vector;

public class Vec2<E> extends Vector<E> {
    public Vec2(E x, E y) {
        super(2);
        this.add(x);
        this.add(y);
    }

    public void setX(E x) {
        this.set(0, x);
    }

    public void setY(E y) {
        this.set(1, y);
    }

    public E getX() {
        return this.get(0);
    }

    public E getY() {
        return this.get(1);
    }
}
