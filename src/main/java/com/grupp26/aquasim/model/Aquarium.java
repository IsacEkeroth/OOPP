package com.grupp26.aquasim.model;

import java.util.ArrayList;

public class Aquarium implements IAquarium {
    private final ArrayList<IFish> fishList = new ArrayList<>();
    private final ArrayList<IDecoration> decorationList = new ArrayList<>();
    private final ArrayList<IEdible> foodList = new ArrayList<>();

    private final Vec2<Integer> aquariumSize;
    private int HEIGHT_OFFSET = 25; // Adjustable offset to account for window borders

    // Considered ph as a double first, but x10 it instead to keep them all as int
    // Maybe all should be double for more precision? or x10 like ph?
    private int temperature; // Celsius,
    private int algaeLevel; // 0-100 scale, bigger scale so it can increment by 1 each tick?
    private int o2Conc; // mg/L, < 3 harmful for fish
    private int salinity; // g/L, = 0 for freshwater, > 30 for seawater
    private int pHLevel; // (x10) 0-140 scale, 65-80 ideal (freshwater)
    // Do we want more attributes? Light level, cleanliness, glass-cleanliness?

    public Aquarium(int width, int height) {
        aquariumSize = new Vec2<>(width, height - HEIGHT_OFFSET);
        defaultAttributes();
    }

    // Default size 1280x720
    public Aquarium() {
        this(1280, 720);
    }

    private void defaultAttributes() {
        this.temperature = 25; // default temperature
        this.algaeLevel = 0; // default algae level
        this.o2Conc = 5; // default oxygen concentration
        this.salinity = 0; // freshwater by default
        this.pHLevel = 70; // default pH level
    }

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

    // Temporary method --> Delete later
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
    public Vec2<Integer> clampPosition(Vec2<Integer> pos, Vec2<Integer> size) {
        int clampedX = Math.max(0, Math.min(aquariumSize.getX() - size.getX(), pos.getX()));
        int clampedY = Math.max(0, Math.min(aquariumSize.getY() - size.getY(), pos.getY()));
        return new Vec2<Integer>(clampedX, clampedY);
    }

    @Override
    public Vec2<Integer> getAquariumSize() {
        return new Vec2<Integer>(aquariumSize.getX(), aquariumSize.getY());
    }

    @Override
    public int getTemperature() {
        return temperature;
    }

    @Override
    public void setTemperature(int temp) {
        this.temperature = Math.max(0, Math.min(100, temp)); // Clamp between 0 and 100, fish soup yum.
    }

    @Override
    public int getAlgaeLevel() {
        return algaeLevel;
    }

    @Override
    public void setAlgaeLevel(int level) {
        this.algaeLevel = Math.max(0, Math.min(100, level)); // Clamp between 0 and 100
    }

    @Override
    public int getO2Conc() {
        return o2Conc;
    }

    @Override
    public void setO2Conc(int conc) {
        this.o2Conc = Math.max(0, conc); // Upper limit?
    }

    @Override
    public int getSalinity() {
        return salinity;
    }

    @Override
    public void setSalinity(int salinity) {
        this.salinity = Math.max(0, Math.min(340, salinity)); // Dead sea max ~340 g/L, doesnt support aquatic life
    }

    @Override
    public int getPHLevel() {
        return pHLevel;
    }

    @Override
    public void setPHLevel(int level) {
        this.pHLevel = Math.max(0, Math.min(140, level)); // Clamp between 0 and 140 (x10 scale)
    }

    @Override
    public AquariumState getState() {
        return new AquariumState(new ArrayList<>(this.fishList), new ArrayList<>(this.decorationList),
                new ArrayList<IEdible>(this.foodList),
                this.temperature, this.algaeLevel, this.o2Conc, this.salinity, this.pHLevel);
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
        for (IDecoration decoration : decorationList) {
            if (decoration instanceof ITickable tickDeco) {
                tickDeco.tick();
            }
        }
        for (IEdible edible : foodList) {
            edible.tick();
        }
    }
}
