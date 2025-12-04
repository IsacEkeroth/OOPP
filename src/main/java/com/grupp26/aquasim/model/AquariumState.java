package com.grupp26.aquasim.model;

import java.util.ArrayList;

public class AquariumState {
    ArrayList<IFish> fish = new ArrayList<>();
    ArrayList<IDecoration> decorations = new ArrayList<>();
    
    //Attributes
    int temperature, algaeLevel, o2Conc, salinity, pHLevel;
    
    public AquariumState(ArrayList<IFish> fishList, ArrayList<IDecoration> decorationList,
                         int temperature, int algaeLevel, int o2Conc, int salinity, int pHLevel) {
        this.fish = fishList;
        this.decorations = decorationList;
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
}
