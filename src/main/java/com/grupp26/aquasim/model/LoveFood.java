package com.grupp26.aquasim.model;

public class LoveFood extends Food {
    private String TYPE = "LOVE_FOOD";

    LoveFood(Vec3<Integer> pos, int amount, IAquarium aquarium) {
        super(pos, amount, aquarium);
    }

    @Override
    public void eatenBy(IFish fish) {
        super.eatenBy(fish);

        fish.setLove(true);
    }

    @Override
    public String getType() {
        return TYPE;
    }

}