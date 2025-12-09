package com.grupp26.aquasim.model;

import java.util.ArrayList;
import java.util.List;

public class HungerState implements IFishState{
    private IFish fish;
    private TargetMove targetmove;
    private IAquarium aquarium;
    private IBehaviour context;

    public HungerState(IBehaviour context, IFish fish, TargetMove targetmove, IAquarium aquarium){
        this.context = context;
        this.fish = fish;
        this.targetmove = targetmove;
        this.aquarium = aquarium;
    }

    private void findFood(){
        List<IEdible> food = new ArrayList<>(aquarium.getFood());
        IEdible closestFood = null;
        int minRange = 5000;
        // unreachable
        int xaxis;
        int yaxis;
        int hypotenuse;
        for (IEdible edible : food){
            xaxis = edible.getPos().getX() - this.fish.getPos().getX();
            yaxis = edible.getPos().getY() - this.fish.getPos().getY();
            hypotenuse = (int) Math.hypot(xaxis,yaxis);
            if(hypotenuse < minRange){
                closestFood = edible;
                minRange = hypotenuse;
            }
            //check for the closest food
        }
        if(closestFood != null){
            this.targetmove.setTarget(closestFood.getPos());
        }
        //target is the closest food       
    }

    private IFishState checkState(){
        if (this.fish.getHunger() < context.getHungryAt() || aquarium.getFood() == null || aquarium.getFood().isEmpty()){
            return context.getPassiveState();
        }
        else{
            return context.getHungerState();
        }
    }


    @Override
    public void update(){
        IFishState newState = checkState();
        if(!newState.equals(this)){
            context.setState(newState);
        }
        else{
            findFood();
            this.targetmove.move(this.fish);
        }
    }
}
