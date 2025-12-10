package com.grupp26.aquasim.model;

public class FoodStaleState implements IFoodState{
    private IEdible food;
    private IFoodBehaviour context;
    private IAquarium aquarium;

    public FoodStaleState(IFoodBehaviour context, IEdible food, IAquarium aquarium){
        this.context = context;
        this.food = food;
        this.aquarium = aquarium;
    }

    private boolean hasReachedBottom(){
        return (food.getPos().getY() > aquarium.getAquariumSize().getY()-50);
    }

    //kolla FoodSinkingState för kommentar på checkState
    private IFoodState checkState(){
        if (food.isEaten()){
            return context.getEatenState();
        }
        else if (!hasReachedBottom()){
            return context.getSinkingState();
        }
        else{
            return context.getStaleState();
        }
    }

    @Override
    public void update(){
        IFoodState newState = checkState();
        context.setState(newState);
    }
}
