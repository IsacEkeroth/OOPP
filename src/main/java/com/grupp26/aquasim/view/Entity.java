package com.grupp26.aquasim.view;

import java.awt.Point;
import java.awt.image.BufferedImage;

public class Entity implements IRenderedEntity {
    Point pos;
    int depth;
    Point size;
    BufferedImage image;

    public Entity(Point pos, int depth, Point size, BufferedImage image) {
        this.pos = pos;
        this.depth = depth;
        this.size = size;
        this.image = image;
    }

    @Override
    public Point getPos() {
        return new Point(pos);
    }

    public void setPos(Point pos) {
        this.pos.x = (int) pos.getX();
        this.pos.y = (int) pos.getY();
    }

    public int getDepth() {
        return depth;
    }

    @Override
    public Point getSize() {
        return new Point(size);

    }

    @Override
    public BufferedImage getImage() {
        // this is a reference which might be bad
        return image;
    }

}
