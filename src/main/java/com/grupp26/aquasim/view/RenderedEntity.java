package com.grupp26.aquasim.view;

import com.grupp26.aquasim.model.IEntity;

import java.awt.Point;
import java.awt.image.BufferedImage;

public class RenderedEntity implements IRenderedEntity {
    Point pos;
    int depth;
    Point size;
    BufferedImage image;

    public RenderedEntity(Point pos, int depth, Point size, BufferedImage image) {
        this.pos = pos;
        this.depth = depth;
        this.size = size;
        this.image = image;
    }

    public RenderedEntity(IEntity entity) {
        this.pos = entity.getPos();
        this.depth = entity.getDepth();
        this.size = entity.getSize();
        this.image = SpriteManager.imageFromString(entity.getImagePath());
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
        return image;
    }

}
