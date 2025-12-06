package com.grupp26.aquasim.model;

public class FishFactory {

    public static Fish createFish(IAquarium aquarium, int hunger, int health, int baseSpeed, Vec3<Integer> pos) {

        Fish fish = new Fish(aquarium);

        fish.setHunger(hunger);

        fish.setHealth(health);

        fish.setBaseSpeed(baseSpeed);

        fish.setPos(pos.getX(), pos.getY(), pos.getZ());

        return fish;

    }

}
