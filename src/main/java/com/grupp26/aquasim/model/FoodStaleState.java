package com.grupp26.aquasim.model;

/**
 * Represents the state where the food-object is lying still on the aquarium floor.
 * <p>
 *     In this state the food object does not execute any movement. <br>
 *     The class is responsible for monitoring whether the food has been consumed or if the
 *     conditions of remaining have changed, initiating transitions to
 *     {@link FoodEatenState} or {@link FoodSinkingState} accordingly.
 * </p>
 */
public class FoodStaleState implements IFoodState{
    private IEdible food;
    private IFoodBehaviour context;
    private IAquarium aquarium;

    public FoodStaleState(IFoodBehaviour context, IEdible food, IAquarium aquarium) {
        this.context = context;
        this.food = food;
        this.aquarium = aquarium;
    }
    

    @Override
    public void update() {
        food.setAmount(food.getAmount() - 1);
        if (food.isEaten()){
            context.setState(context.getEatenState());
        }
    }
}
