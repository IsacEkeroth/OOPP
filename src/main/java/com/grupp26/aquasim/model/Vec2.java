package com.grupp26.aquasim.model;

import java.util.Vector;

/**
 * A generic container class representing a two-dimensional vector or coordinate.
 * <p>
 *     This class extends {@link java.util.Vector} and is designed to store exactly
 *     two elements of type {@code E}. It is primarily used to represent sizes, positions,
 *     or directions within a two-dimensional plane in the simulation.
 * </p>
 * @param <E> The type of elements stored in the vector.
 */
public class Vec2<E> extends Vector<E> {
    public Vec2(E x, E y) {
        super(2);
        this.add(x);
        this.add(y);
    }

    public Vec2(Vec2<E> vec2) {
        this(vec2.getX(), vec2.getY());
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
