package com.grupp26.aquasim.model;

import java.awt.*;

public interface IEntity {
    Point getSize();
    
    int getDepth();
    
    Point getPos();
    
    void setPos(Point pos);
    
    String getEntity_ID();

    // Temporär
    String getEntityType();

}
