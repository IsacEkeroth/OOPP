package com.grupp26.aquasim.model;

import java.util.Vector;

/**
 * A generic container class representing a three-dimensional vector or coordinate.
 * <p>
 *     This class extends {@link java.util.Vector} and is designed to store exactly
 *     three elements of type {@code E}. In this simulation, the Z-axis is primarily
 *     used to manage <b>depth</b>, determining the rendering order of entities.
 * </p>
 * @param <E> The type of elements stored in the vector.
 */
public class Vec3<E> extends Vector<E> {
    public Vec3(E x, E y, E z) {
        super(3);
        this.add(x);
        this.add(y);
        this.add(z);
    }

    public Vec3(Vec3<E> vec3) {
        this(vec3.getX(), vec3.getY(), vec3.getZ());
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
