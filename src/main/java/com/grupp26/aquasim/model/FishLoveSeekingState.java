package com.grupp26.aquasim.model;

import java.util.ArrayList;
import java.util.List;

public class FishLoveSeekingState implements IFishState {
    private IFish fish;
    private IAquarium aquarium;
    private IFishBehaviour context;
    private TargetMove targetmove;
    private IFish closestPartner;

    public FishLoveSeekingState(IFishBehaviour context, IFish fish, TargetMove targetmove, IAquarium aquarium) {
        this.context = context;
        this.fish = fish;
        this.aquarium = aquarium;
        this.targetmove = targetmove;
        closestPartner = null;
    }

    private void findPartner(){
        List<IFish> partners = new ArrayList<>(aquarium.getinLoveFish(this.fish));
        closestPartner = null;
        int minRange = 50000; // omöjligt att nå
        int xaxis;
        int yaxis;
        int hypotenuse;
        for (IFish fishy : partners) {
            xaxis = fishy.getPos().getX() - this.fish.getPos().getX();
            yaxis = fishy.getPos().getY() - this.fish.getPos().getY();
            hypotenuse = (int) Math.hypot(xaxis, yaxis);
            if (hypotenuse < minRange) {
                closestPartner = fishy;
                minRange = hypotenuse;
            }
            // hitta närmaste partner
            // kopierad logik från FishHungerState
        }
    }

    private void moveFish(){
        if(closestPartner != null){
            targetmove.setTarget(closestPartner.getPos());
            targetmove.move(fish);
        }
        //förhindrar krasch när man lägger till för många fiskar för snabbt
    }

    private boolean hasFoundPartner(){
        if(closestPartner==null){
            return false;
        }

        double dx = Math.abs(fish.getPos().getX() - closestPartner.getPos().getX());
        double dy = Math.abs(fish.getPos().getY() - closestPartner.getPos().getY());

        if (dx <= 10 && dy <= 10 && !fish.canSpawnChild()) {
            fish.setLove(false); //för att stoppa andra fiskar att "hoppa in"
            closestPartner.setLove(false);
            closestPartner.setSpawnChild(true);
            return true;
        }
        return false;
    }

    private IFishState checkState(){
        if(!fish.isAlive()){
            return context.getDeathState();
        }
        else if (hasFoundPartner()){
            return context.getMatingState();
        }
        else{
            return this;
        }
        // tänker här att fisken inte kan komma ut ur loveseeking om den inte hittat en partner eller dött
        // lite som man brukar se i dokumentärer 
    }

    public void update() {
        IFishState newState = checkState();
        if(!newState.equals(this)){
            context.setState(newState);
        }
        else{
            findPartner();
            moveFish();
        }
    }

    @Override
    public double getDirection() {
        return targetmove.getDirection();
    }

}
