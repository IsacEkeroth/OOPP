package com.grupp26.aquasim.model;

import java.util.ArrayList;

/**
 * Represents the current state of an {@link Aquarium}.
 * <p>
 * This class gathers all relevant data for an Aquarium, like fishes, decorations, food, etc.
 * at a specific time.
 * <p>
 * It's main purpose is to transfer data from the Model to the View in a read-only fashion.
 * This way the Views representation of the state is disconnected from the models internal logic.
 * </p>
 */
public class AquariumState {
    ArrayList<IFish> fish = new ArrayList<>();
    ArrayList<IDecoration> decorations = new ArrayList<>();
    ArrayList<IEdible> food = new ArrayList<>();

    // Attributes
    int temperature, algaeLevel, o2Conc, salinity, pHLevel;

    public AquariumState(ArrayList<IFish> fishList, ArrayList<IDecoration> decorationList, ArrayList<IEdible> foodList,
            int temperature, int algaeLevel, int o2Conc, int salinity, int pHLevel) {
        this.fish = fishList;
        this.decorations = decorationList;
        this.food = foodList;
        this.temperature = temperature;
        this.algaeLevel = algaeLevel;
        this.o2Conc = o2Conc;
        this.salinity = salinity;
        this.pHLevel = pHLevel;
    }

    public ArrayList<IFish> getFish() {
        return new ArrayList<>(this.fish);
    }

    public ArrayList<IDecoration> getDecorations() {
        return new ArrayList<>(this.decorations);
    }

    public ArrayList<IEdible> getFood() {
        return new ArrayList<>(this.food);
    }

    public int getTemperature() {
        return this.temperature;
    }

    public int getAlgaeLevel() {
        return this.algaeLevel;
    }

    public int getO2Conc() {
        return this.o2Conc;
    }

    public int getSalinity() {
        return this.salinity;
    }

    public int getPHLevel() {
        return this.pHLevel;
    }

}
