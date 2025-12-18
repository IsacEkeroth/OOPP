package com.grupp26.aquasim.model;

import java.awt.*;

/**
 * Represents the base-entity for an object in the simulation with all its properties/data.
 * <p>
 * The class functions as a data container for all common attributes.
 * It is the foundation for all object that exists within the Aquarium
 * and is to be rendered by the View.
 * </p>
 */
public class Entity implements IEntity {
    Point pos;
    int depth;
    Point size;
    boolean isFacingRight;

    String entity_type;
    String entity_ID;

    public Entity(Vec3<Integer> pos, Vec2<Integer> size, String type, String id, boolean isFacingRight) {
        this.pos = new Point(pos.getX(), pos.getY());
        this.depth = pos.getZ();
        this.size = new Point(size.getX(), size.getY());
        this.isFacingRight = isFacingRight;

        // TODO -- "String type" behöver nog vara "state" ifall animationen ska kunna
        // byta sekvens --
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

    public boolean isFacingRight() {
        return isFacingRight;
    }
}
