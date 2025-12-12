package com.grupp26.aquasim.model;

public class FoodFactory {
    IAquarium aquarium;

    public FoodFactory(IAquarium aquarium) {
        this.aquarium = aquarium;
    }

    public IEdible createFood(String type) {

        int centerX = aquarium.getAquariumSize().getX() / 2;
        int centerY = 50;
        int centerZ = 1;
        return new Food(new Vec3<Integer>(centerX, centerY, centerZ), 50, aquarium);
    }

}
