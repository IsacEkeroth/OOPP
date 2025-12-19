package com.grupp26.aquasim.model;

import java.util.function.BiFunction;

/**
 * A data container that defines the specific properties and behaviours
 * for a specific fish species.
 * <p>
 *     The class works as a blueprint that stores unchangeable data like: name of the species,
 *     health, speed, etc.
 * </p>
 */
public class FishTypeData implements IFishTypeData {

    private final String speciesName;
    private final int maxHealth;
    private final int baseSpeed;
    private final int bitingPower;

    // BiFunction måste tydligen användas för att låta godtycklig fishbehaviour passeras utan ytterligare kodkomplexitet
    private final BiFunction<IFish, Double, IFishBehaviour> behaviourFactory;

    public FishTypeData(String speciesName, int maxHealth, int baseSpeed, int bitingPower, BiFunction<IFish, Double, IFishBehaviour> behaviourFactory) {
        this.speciesName = speciesName;
        this.maxHealth = maxHealth;
        this.baseSpeed = baseSpeed;
        this.bitingPower = bitingPower;
        this.behaviourFactory = behaviourFactory;
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
    public int getBitingPower() {
        return bitingPower;
    }

    @Override
    public String getSpeciesName() {
        return speciesName;
    }

    @Override
    public IFishBehaviour createBehaviour(Fish fish, double initialDirection) {
        return behaviourFactory.apply(fish, initialDirection);
    }

}
