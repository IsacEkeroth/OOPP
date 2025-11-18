package view;

import java.awt.Point;
import java.awt.image.BufferedImage;

public class FishEntity implements IRenderedEntity {
    Point pos;
    Point size;
    BufferedImage image;

    FishEntity(Point pos, Point size, BufferedImage image) {
        this.pos = pos;
        this.size = size;
        this.image = image;
    }

    @Override
    public Point getPos() {
        return new Point(pos);
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
