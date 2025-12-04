package com.grupp26.aquasim.model;

public class PassiveState implements IFishState{
    private IFish fish;
    private SimpleMove simplemove;
    private IBehaviour context;
    private IAquarium aquarium;

    public PassiveState(IBehaviour context, IFish fish, SimpleMove simplemove, IAquarium aquarium){
        this.context = context;
        this.fish = fish;
        this.simplemove = simplemove;
        this.aquarium = aquarium;
    }

    private void checkState(){
        if (this.fish.getHunger() < context.getHungryAt() && aquarium.getFood() != null && !aquarium.getFood().isEmpty()){
            context.setState(context.getHungerState());
            //if there is food in the aquarium and you are hungry, enter hungry mode
        }
    }

    @Override
    public void update(){
        checkState();
        this.simplemove.move(fish);
        //check state, if no switch, continue swimming
    }

}
