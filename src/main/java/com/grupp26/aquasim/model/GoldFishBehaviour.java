package com.grupp26.aquasim.model;

import java.util.ArrayList;
import java.util.List;

public class GoldFishBehaviour implements IBehaviour{
    private IAquarium aquarium;
    private IFish fish;
    private SimpleMove simplemove;
    private TargetMove targetmove;

    public GoldFishBehaviour(IFish fish, int initialDirection){
        this.fish = fish;
        this.aquarium = fish.getAquarium();
        this.simplemove = new SimpleMove(this.aquarium, initialDirection);
        this.targetmove = new TargetMove(this.aquarium, initialDirection);
    }

    private boolean CheckHunger(){
        if (this.fish.getHunger() < 30 && aquarium.getFood() != null && !aquarium.getFood().isEmpty()){ 
            //check if the fish is hungry
            //check if there is food in the aquarium
            return true;
        }
        else{
            return false;
        }
    }

    private void HungerTarget(){
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

    private boolean HungerState(){
        if (CheckHunger()){
            HungerTarget();
            this.targetmove.move(this.fish);
            //fail-safe? Will only run if CheckHunger is true, so there needs to be food
            //if there is food there is a target
            if(!CheckHunger()){
                this.targetmove.setTarget(null);
                //remove target immediately if fish is not hungry or there is no food in the aquarium
            }
            return true;
        }
        else{
            return false;
        }
    }

    private boolean PassiveState(){
        if(this.targetmove.getTarget() == null){
            this.simplemove.move(this.fish);
            return true;
        }
        else{
            return false;
        }
    }

    public void update(){
        if (HungerState()) return;
        if (PassiveState()) return;
    }

}

//inte riktigt state-pattern men kan uppgraderas om det behövs
//genom att implementera både generella och individuella states