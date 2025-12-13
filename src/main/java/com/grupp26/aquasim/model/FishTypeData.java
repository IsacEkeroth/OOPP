package com.grupp26.aquasim.model;

public class FishTypeData implements IFishTypeData {

    private final String speciesName;
    private final int maxHealth;
    private final int baseSpeed;

    public FishTypeData(String speciesName, int maxHealth, int baseSpeed) {
        this.speciesName = speciesName;
        this.maxHealth = maxHealth;
        this.baseSpeed = baseSpeed;
    }

    @Override
    public int getMaxHealth() {
        return maxHealth;
    }

    @Override
    public int getBaseSpeed() {
        return baseSpeed;
    }

    @Override
    public String getSpeciesName() {
        return speciesName;
    }

}
