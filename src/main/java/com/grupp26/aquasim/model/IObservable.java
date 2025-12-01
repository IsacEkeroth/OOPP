package com.grupp26.aquasim.model;

import com.grupp26.aquasim.view.IObserver;

public interface IObservable {
    void addObserver(IObserver observer);
    void removeObserver(IObserver observer);
    void notifyObservers();
}
