package com.grupp26.aquasim.model;

import com.grupp26.aquasim.view.Entity;
import com.grupp26.aquasim.model.IObservable;
import com.grupp26.aquasim.view.IObserver;

import java.util.ArrayList;
import java.util.List;


public class TmpModelFacade implements IObservable {

    List<Entity> entities = new ArrayList<>();
    List<IObserver> observers = new ArrayList<>();


    public void addFish() {
        // lägger till en fisk
    }

    // Obs! Antagligen inte skicka med listan med entities
    @Override
    public void notifyObservers() {
        for(IObserver o : observers) {
            o.actOnNotification(entities);
        }
    }

    @Override
    public void addObserver(IObserver o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(IObserver o) {
        observers.remove(o);
    }
}
