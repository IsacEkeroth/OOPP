package com.grupp26.aquasim.model;

public class FishFactory {

    public static Goldfish createFish(IAquarium aquarium, int hunger, int health, int baseSpeed, Vec3<Integer> pos) {

        Goldfish fish = new Goldfish(aquarium);

        // ska vi ha en fish.setAge()? kanske för ev. debugging?

        fish.setHunger(hunger);

        fish.setHealth(health);

        fish.setBaseSpeed(baseSpeed);

        fish.setPos(pos.getX(), pos.getY(), pos.getZ());

        return fish;

    }

}
