package com.grupp26.aquasim.model;

import java.awt.*;

public class Entity implements IEntity{
    Point pos;
    int depth;
    Point size;
    String imagePath;
    
    public Entity(Vec3<Integer> pos, Vec2<Integer> size, String imagePath) {
        this.pos = new Point(pos.getX(), pos.getY());
        this.depth = pos.getZ();
        this.size = new Point(size.getX(), size.getY());
        this.imagePath = imagePath;
    }
    
    @Override
    public Point getSize() {
        return new Point(size);
    }
    
    @Override
    public int getDepth() {
        return depth;
    }
    
    @Override
    public Point getPos() {
        return new Point(pos);
    }
    
    @Override
    public void setPos(Point newPos) {
        this.pos = new Point(newPos);
    }
    
    @Override
    public String getImagePath() {
        return imagePath;
    }
}
