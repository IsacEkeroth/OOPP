package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class Aquarium implements IAquarium {
    ArrayList<IFish> fishList;
    
    final Point aquariumSize = new Point(720, 540);
    
    @Override
    public void addFish(IFish fish) {
        this.fishList.add(fish);
    }
    
    @Override
    public void removeFish(IFish fish) {
        int fishIndex = this.fishList.indexOf(fish);
        if (fishIndex >= 0) {
            this.fishList.remove(fishIndex);
        }
    }
    
    // Assuming fish are rectangular and not rotated
    @Override
    public boolean isValidPosition(Vector<Integer> pos, Vector<Integer> size) {
        int x = pos.get(0);
        int y = pos.get(1);
        if (0 <= x && x <= this.aquariumSize.getX() - size.get(0)
            && 0 <= y && y <= this.aquariumSize.getY() - size.get(1)) {
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public Point getAquariumSize() {
        return this.aquariumSize;
    }
    
}
