package com.grupp26.aquasim.model;

import java.util.ArrayList;
import java.util.List;

public class HungerState implements IFishState{
    private IFish fish;
    private TargetMove targetmove;
    private IAquarium aquarium;
    private IBehaviour context;
    private IEdible closestFood;

    public HungerState(IBehaviour context, IFish fish, TargetMove targetmove, IAquarium aquarium){
        this.context = context;
        this.fish = fish;
        this.targetmove = targetmove;
        this.aquarium = aquarium;
    }

    private void findFood(){
        List<IEdible> food = new ArrayList<>(aquarium.getFood());
        closestFood = null;
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
    }

    private void EatClosestFood(){
        double dx = Math.abs(fish.getPos().getX() - closestFood.getPos().getX());
        double dy = Math.abs(fish.getPos().getY() - closestFood.getPos().getY());

        if (dx <= 10 && dy <= 10) {
            closestFood.eat(fish.getBitingPower()); 
            fish.setHunger(fish.getHunger()-closestFood.getAmount());
        }
    }

    private boolean eatableFood(){
        for (IEdible edible : aquarium.getFood()){
            if (!edible.isEaten()){
                return true;
            }
        }
        return false;
    }

    private IFishState checkState(){
        if (this.fish.getHunger() < context.getHungryAt() || aquarium.getFood() == null || aquarium.getFood().isEmpty() || !eatableFood()){
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
            EatClosestFood();
        }
    }
}
