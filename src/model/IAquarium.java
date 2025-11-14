package model;

import java.util.List;
import java.util.Vector;
import java.awt.Dimension;
import java.awt.Point;

public interface IAquarium {
    // comment = private attributes
    // List<IFish> fish;
    // Point aquariumSize = new Point(720, 540);

    void addFish(IFish fish);

    void removeFish(IFish fish);

    boolean isValidPosition(Vector<Integer> pos, Vector<Integer> size);
    
    Point getAquariumSize();
}
