package com.grupp26.aquasim.view;

/**
 * Defines the contract between the object that are to observe another object
 * through the Observer Design Pattern.
 * <p>
 * The method {@link #update()} handles what the observer should do when notified.
 */
public interface IObserver {
    void update();
}
