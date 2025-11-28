package com.grupp26.aquasim.model;

import com.grupp26.aquasim.view.IObserver;



public interface IObservable {


    void notifyObservers();

    void addObserver(IObserver o);

    void removeObserver(IObserver o);

}
