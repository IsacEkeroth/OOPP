package model;

import java.util.List;
import java.util.Vector;
import java.awt.Dimension;
import java.awt.Point;

public interface IAquarium {
    // comment = private attributes
    // List<IFish> fish;
    // Point size = new Point(720, 540);

    void addFish();

    void removeFish();

    boolean isValidPosition();
}
