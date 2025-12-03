package com.grupp26.aquasim.model;

import java.util.ArrayList;

public class Aquarium implements IAquarium {
    ArrayList<IFish> fishList = new ArrayList<>();
    ArrayList<IDecoration> decorationList = new ArrayList<>();

    final Vec2<Integer> aquariumSize = new Vec2<Integer>(1280, 720);

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
    
    @Override
    public void addDecoration(IDecoration decoration) {
        this.decorationList.add(decoration);
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
    public Vec2<Integer> getAquariumSize() {
        return new Vec2<Integer>(aquariumSize.getX(), aquariumSize.getY());
    }

    @Override
    public void tick() {
        for (IFish fish : fishList) {
            fish.tick();
        }
        for (IDecoration decoration : decorationList) {
            if (decoration instanceof ITickable tickDeco) {
                tickDeco.tick();
            }
        }
    }
    
    @Override
    public AquariumState getState() {
        return new AquariumState(new ArrayList<>(this.fishList), new ArrayList<>(this.decorationList));
    }
}
