package view;

import java.awt.Point;
import java.awt.image.BufferedImage;

public interface IRenderedEntity {
    Point getSize();

    Point getPos();

    BufferedImage getImage();
}
