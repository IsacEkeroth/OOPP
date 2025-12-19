package com.grupp26.aquasim.model;

public class LoveFood extends Food {
    LoveFood(Vec3<Integer> pos, int amount, IAquarium aquarium) {
        super(pos, amount, aquarium);
    }

    @Override
    public void eatenBy(IFish fish) {
        super.eatenBy(fish);

        fish.setLove(true);
    }

}