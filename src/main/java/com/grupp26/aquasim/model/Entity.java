package com.grupp26.aquasim.model;

import java.awt.*;

/**
 * Represents the base-entity for an object in the simulation with all its
 * properties/data.
 * <p>
 * The class functions as a data container for all common attributes.
 * It is the foundation for all object that exists within the Aquarium
 * and is to be rendered by the View.
 * </p>
 */
public class Entity implements IEntity {
    private Point pos;
    private int depth;
    private Point size;
    private boolean isFacingRight;
    private String entity_type;
    private String entity_ID;

    public Entity(Vec3<Integer> pos, Vec2<Integer> size, String type, String id, boolean isFacingRight) {
        this.pos = new Point(pos.getX(), pos.getY());
        this.depth = pos.getZ();
        this.size = new Point(size.getX(), size.getY());
        this.isFacingRight = isFacingRight;
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

    @Override
    public String getEntityType() {
        return entity_type;
    }

    @Override
    public boolean isFacingRight() {
        return isFacingRight;
    }
}
