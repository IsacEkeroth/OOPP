package com.grupp26.aquasim.model;

public interface IBehaviour {
    //update(fish) behöver inte fish om behaviour ska gälla för en fisk
    //ska ta in alla IMovement också
    void update();
}
