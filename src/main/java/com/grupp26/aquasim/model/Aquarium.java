package com.grupp26.aquasim.model;

import java.util.ArrayList;

public class Aquarium implements IAquarium {
    ArrayList<IFish> fishList;

    final Vec2<Integer> aquariumSize = new Vec2(720, 540);

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
    public boolean isValidPosition(Vec2<Integer> pos, Vec2<Integer> size) {
        if ((0 <= pos.getX()) && (pos.getX() <= aquariumSize.getX() - size.getX())
                && (0 <= pos.getY()) && (pos.getY() <= (aquariumSize.getY() - size.getY()))) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Vec2 getAquariumSize() {
        return new Vec2(aquariumSize.getX(), aquariumSize.getY());
    }

}
