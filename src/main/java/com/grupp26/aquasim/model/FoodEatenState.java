package com.grupp26.aquasim.model;

/**
 * Represents the state of a food object once it has been fully consumed.
 * <p>
 *     This is a terminal state in the food's lifecycle. In this state,
 *     the food object ceases all interactions and movement, as it it considered
 *     removed from the simulation.
 * </p>
 */
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
