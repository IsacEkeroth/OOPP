package com.grupp26.aquasim.view;

import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 * Defines the contract for an object (entity) which can be rendered in the view.
 * <p>
 * This interface makes sure that all objects which should be drawn actually contains
 * the necessary information for that purpose.
 */
public interface IRenderedEntity {
    Point getSize();

    int getDepth();

    Point getPos();

    void setPos(Point pos);

    BufferedImage getImage();

    int getCurrentTick();

    String getType();

    boolean isFlipped();
}
