package com.grupp26.aquasim.view;


import java.util.List;

public interface IObserver {

    void actOnNotification(List<Entity> entites);
}
