package main.java.com.grupp26.aquasim.model;

import java.util.Vector;

public class Vec3<E> extends Vector<E> {

    public Vec3(E x, E y, E z) {
        super(3);
        this.setX(x);
        this.setY(y);
        this.setZ(z);
    }

    public void setX(E x) {
        this.set(0, x);
    }

    public void setY(E y) {
        this.set(1, y);
    }

    public void setZ(E z) {
        this.set(2, z);
    }

    public E getX() {
        return this.get(0);
    }

    public E getY() {
        return this.get(1);
    }

    public E getZ() {
        return this.get(2);
    }
}
