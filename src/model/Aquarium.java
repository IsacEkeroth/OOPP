package model;

import java.awt.*;
import java.util.List;

public class Aquarium implements IAquarium {
    List<IFish> fishList;
    final Point size = new Point(720, 540);
    
    @Override
    public void addFish(IFish fish) {
        this.fishList.add(fish);
        // Fish factory somewhere else that creates the fish?
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
    public boolean isValidPosition(int x, int y, int fishX, int fishY) {
        if (0 <= x && x <= this.size.getX() - fishX
            && 0 <= y && y <= this.size.getY() - fishY) {
            return true;
        } else {
            return false;
        }
    }
    
    // Probably never used
    @Override
    public Point getSize() {
        return this.size;
    }
    
    // Sends fishList and size? to view-adapter
}
