package com.grupp26.aquasim.view;

import java.awt.Point;
import java.awt.image.BufferedImage;

public interface IRenderedEntity {
    Point getSize();

    int getDepth();

    Point getPos();

    void setPos(Point pos);

    BufferedImage getImage();

    int getCurrentTick();
}
