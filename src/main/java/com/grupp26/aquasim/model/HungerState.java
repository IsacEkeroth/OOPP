package com.grupp26.aquasim.model;

import java.util.ArrayList;
import java.util.List;

public class HungerState implements IFishState{
    private IFish fish;
    private TargetMove targetmove;
    private IAquarium aquarium;

    public HungerState(IFish fish, TargetMove targetmove, IAquarium aquarium){
        this.fish = fish;
        this.targetmove = targetmove;
        this.aquarium = aquarium;
    }

    private void findFood(){
        List<IEdible> food = new ArrayList<>(aquarium.getFood());
        IEdible closestFood = food.get(0);
        int minRange = 5000;
        // unreachable
        int xaxis;
        int yaxis;
        int hypothenus;
        for (IEdible edible : food){
            xaxis = edible.getPos().getX() - this.fish.getPos().getX();
            yaxis = edible.getPos().getY() - this.fish.getPos().getY();
            hypothenus = (int) Math.hypot(xaxis,yaxis);
            if(hypothenus < minRange){
                closestFood = edible;
                minRange = hypothenus;
            }
            //check for the closest food
        }
        this.targetmove.setTarget(closestFood.getPos());
        //target is the closest food       
    }

    @Override
    public void update(){
        findFood();
        this.targetmove.move(this.fish);
    }
}
