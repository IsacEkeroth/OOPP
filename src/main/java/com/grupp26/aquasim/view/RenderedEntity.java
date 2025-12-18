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
    Point pos;
    int depth;
    Point size;

    String entityID;
    String entity_type;
    BufferedImage image;
    boolean isFlipped;
    private int currentTick;

    public RenderedEntity(Point pos, int depth, Point size, BufferedImage image, boolean isFlipped) {
        this.pos = pos;
        this.depth = depth;
        this.size = size;
        this.image = image;
        this.isFlipped = isFlipped;
    }

    public RenderedEntity(IEntity entity, int currentTick) {
        this.pos = entity.getPos();
        this.depth = entity.getDepth();
        this.size = entity.getSize();

        this.entityID = entity.getEntity_ID();
        this.entity_type = entity.getEntityType();
        this.currentTick = currentTick;
        // TODO -- Kom ihåg: entity_type behöver vara state om animationen ska kunna
        // bero på tillstånd --
        // TODO -- Eller kanske snarare OCKSÅ ett state, man kanske behöver båda trots
        // allt --
        this.image = SpriteManager.getSprite(this.entity_type, this.currentTick);
        this.isFlipped = entity.isFacingRight();
    }

    @Override
    public String getType() {
        return this.entity_type;
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

    public boolean isFlipped() {
        return isFlipped;
    }

}
