package com.grupp26.aquasim.model;

import java.awt.*;

public class Entity implements IEntity{
    Point pos;
    int depth;
    Point size;

    String entity_type;
    String entity_ID;


    public Entity(Vec3<Integer> pos, Vec2<Integer> size, String type, String id) {
        this.pos = new Point(pos.getX(), pos.getY());
        this.depth = pos.getZ();
        this.size = new Point(size.getX(), size.getY());

        this.entity_type = type;
        this.entity_ID = id;
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
    public String getEntity_ID() {
        return entity_ID;
    }
    // Temporär
    @Override
    public String getEntityType() {
        return entity_type;
    }

}
