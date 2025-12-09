package com.grupp26.aquasim.view;

import com.grupp26.aquasim.model.IEntity;

import java.awt.Point;
import java.awt.image.BufferedImage;


public class RenderedEntity implements IRenderedEntity {
    Point pos;
    int depth;
    Point size;

    String entityID;
    String entity_type;
    BufferedImage image;

    private int currentTick;




    public RenderedEntity(Point pos, int depth, Point size, BufferedImage image) {
        this.pos = pos;
        this.depth = depth;
        this.size = size;
        this.image = image;
    }
    
    public RenderedEntity(IEntity entity, int currentTick) {
        this.pos = entity.getPos();
        this.depth = entity.getDepth();
        this.size = entity.getSize();

        this.entityID = entity.getEntity_ID();
        this.entity_type = entity.getEntityType();
        this.currentTick = currentTick;

        this.image = SpriteManager.getFrame(this.entity_type, this.currentTick);


    }




    @Override
    public int getCurrentTick() {
        return this.currentTick;
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
