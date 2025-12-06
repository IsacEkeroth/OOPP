package com.grupp26.aquasim.model;

import java.util.ArrayList;

public class AquariumState {
    ArrayList<IFish> fish = new ArrayList<>();
    ArrayList<IDecoration> decorations = new ArrayList<>();

    public AquariumState(ArrayList<IFish> fishList, ArrayList<IDecoration> decorationList) {
        this.fish = fishList;
        this.decorations = decorationList;
    }

    public ArrayList<IFish> getFish() {
        return new ArrayList<>(this.fish);
    }

    public ArrayList<IDecoration> getDecorations() {
        return new ArrayList<>(this.decorations);
    }
}
