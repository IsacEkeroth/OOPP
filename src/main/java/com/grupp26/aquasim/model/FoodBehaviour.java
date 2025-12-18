package com.grupp26.aquasim.model;

/**
 * Handles the logic for the foods behaviour and physical state through a State Design Pattern.
 * <p>
 *     The class functions as context in a State Pattern and delegates decisions regarding
 *     food-interactions (stale, sinking, eaten) to different {@link IFoodState}-implementations.
 *     <br>
 *     Through centralizing the states in private variables, the class provides dynamic behaviour
 *     transitions throughout the food object's lifecycle.
 * </p>
 */
public class FoodBehaviour implements IFoodBehaviour {

    private IFoodState state;
    private IFoodState staleState;
    private IFoodState sinkingState;
    private IFoodState eatenState;
    private IAquarium aquarium;

    public FoodBehaviour(IEdible food, IAquarium aquarium) {
        this.staleState = new FoodStaleState(this, food, aquarium);
        this.sinkingState = new FoodSinkingState(this, food, aquarium);
        this.eatenState = new FoodEatenState(this, food);
        this.state = staleState;
    }

    @Override
    public void setState(IFoodState newState) {
        this.state = newState;
    }

    @Override
    public IFoodState getStaleState() {
        return this.staleState;
    }

    @Override
    public IFoodState getSinkingState() {
        return this.sinkingState;
    }

    @Override
    public IFoodState getEatenState() {
        return this.eatenState;
    }

    @Override
    public void update() {
        this.state.update();
    }

}
