package com.grupp26.aquasim.view;

import com.grupp26.aquasim.model.IEntity;

import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 *
 * A representation of an Entity which is optimized for rendering in the view.
 * <p>
 * This class extracts necessary information from the games model {@link IEntity}
 * and links this information with the correct graphic from {@link SpriteManager}.
 *
 */
public class RenderedEntity implements IRenderedEntity {
    BufferedImage image;
    private int currentTick;
    IEntity entity;

    public RenderedEntity(IEntity entity, int currentTick) {
        this.entity = entity;
        this.currentTick = currentTick;
        this.image = SpriteManager.getSprite(this.entity.getEntityType(), this.currentTick);
    }

    @Override
    public String getType() {
        return entity.getEntityType();
    }

    @Override
    public int getCurrentTick() {
        return this.currentTick;
    }

    @Override
    public Point getPos() {
        return entity.getPos();
    }

    public void setPos(Point pos) {
        entity.setPos(pos);
    }

    public int getDepth() {
        return entity.getDepth();
    }

    @Override
    public Point getSize() {
        return entity.getSize();

    }

    @Override
    public BufferedImage getImage() {
        // this is a reference which might be bad
        return image;
    }

    public boolean isFlipped() {
        return entity.isFacingRight();
    }

}
