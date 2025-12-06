package com.grupp26.aquasim.model;

import java.util.ArrayList;

public class Aquarium implements IAquarium {
    ArrayList<IFish> fishList = new ArrayList<>();
    ArrayList<IDecoration> decorationList = new ArrayList<>();
    ArrayList<IEdible> foodList = new ArrayList<>();

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
    public void removeLastFish() {
        if (!this.fishList.isEmpty()) {
            this.fishList.remove(fishList.size() - 1);
        }
    }

    @Override
    public void addDecoration(IDecoration decoration) {
        this.decorationList.add(decoration);
    }

    @Override
    public void addFood(IEdible edible) {
        this.foodList.add(edible);
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
    public ArrayList<IEdible> getFood() {
        return new ArrayList<IEdible>(foodList);
    }

    @Override
    public void tick() {
        for (IFish fish : fishList) {
            fish.tick();
        }
    }

    @Override
    public AquariumState getState() {
        return new AquariumState(new ArrayList<>(this.fishList), new ArrayList<>(this.decorationList));
    }
}
