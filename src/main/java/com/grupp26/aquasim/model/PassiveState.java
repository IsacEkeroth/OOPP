package com.grupp26.aquasim.model;

public class PassiveState implements IFishState{
    private IFish fish;
    private SimpleMove simplemove;
    private IFishBehaviour context;
    private IAquarium aquarium;

    public PassiveState(IFishBehaviour context, IFish fish, SimpleMove simplemove, IAquarium aquarium){
        this.context = context;
        this.fish = fish;
        this.simplemove = simplemove;
        this.aquarium = aquarium;
    }

    private boolean isThereFood(){
        for (IEdible edible : aquarium.getFood()){
            if (!edible.isEaten()){
                return true;
            }
        }
        return false;
    }

    private IFishState checkState(){
        if(!fish.isAlive()){
            return context.getDeathState();
        }
        else if (this.fish.getHunger() > context.getHungryAt() && aquarium.getFood() != null && !aquarium.getFood().isEmpty() && isThereFood(){
            return context.getHungerState();
            //if there is food in the aquarium and you are hungry, enter hungry mode
        }
        else{
            return context.getPassiveState();
        }
    }

    @Override
    public void update(){
        IFishState newState = checkState();
        if(!newState.equals(this)){
            context.setState(newState);
        }
        else {
            this.simplemove.move(fish);
        }
        //check state, if no switch, continue swimming
    }

}
