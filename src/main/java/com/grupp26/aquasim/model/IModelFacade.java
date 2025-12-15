package com.grupp26.aquasim.model;

import java.util.ArrayList;

public interface IModelFacade extends IObservable {
    void tick();

    ArrayList<IEntity> getEntities();

    void addFish(int x, int y);

    void removeFish();

    void addDecoration(String type, int x, int y);

    void addFood(String type, int x, int y);
}
