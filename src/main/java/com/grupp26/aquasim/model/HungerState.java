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
        if(closestFood != null){
            this.targetmove.setTarget(closestFood.getPos());
        }
        //target is the closest food       
    }

    private void checkState(){
        if (this.fish.getHunger() > context.getHungryAt() || aquarium.getFood() == null || aquarium.getFood().isEmpty()){
            this.targetmove.setTarget(null);
            context.setState(context.getPassiveState());
            //if you are not hungry or there is no food in the aquarium, enter passive state
        }
        else{
            findFood();
        }
    }


    @Override
    public void update(){
        checkState();
        this.targetmove.move(this.fish);
    }
}
