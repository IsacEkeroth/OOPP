package com.grupp26.aquasim.model;

public class FoodEatenState implements IFoodState{
    private IEdible food;
    private IFoodBehaviour context;

    public FoodEatenState(IFoodBehaviour context, IEdible food){
        this.context = context;
        this.food = food;
    }

    @Override
    public void update(){
        //don't do anything, you're dead
        //cannot switch states, you're dead 
    }
}
