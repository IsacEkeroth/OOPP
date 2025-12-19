package com.grupp26.aquasim.model;

/**
 * Represents the state where the food-object sinks.
 * <p>
 *     In this state the vertical movement of the food is calculated based on the food's amount
 *     to simulate gravity. <br>
 *     The class is responsible for monitoring if the food has reached the bottom of the aquarium
 *     or if it has been eaten, initiating a transition to {@link FoodStaleState} or
 *     {@link FoodEatenState} accordingly.
 * </p>
 */
public class FoodSinkingState implements IFoodState{
    private IEdible food;
    private IFoodBehaviour context;
    private IAquarium aquarium;

    public FoodSinkingState(IFoodBehaviour context, IEdible food, IAquarium aquarium){
        this.context = context;
        this.food = food;
        this.aquarium = aquarium;
    }

    private boolean hasReachedBottom(){
        return (food.getPos().getY() >= aquarium.getAquariumSize().getY() - food.getSize().getY());
    }

    private IFoodState checkState() {
        if (food.isEaten()){
            return context.getEatenState();
        }
        else if (hasReachedBottom()){
            return context.getStaleState();
        }
        else{
            return context.getSinkingState();
        }
    }

    private void sink() {
        // Food with more amount sinks faster
        int sinkFactor = Math.max(1, Math.min(food.getAmount()/100, 5)); // Sink factor between 1 and 5
        food.setPos(food.getPos().getX(), food.getPos().getY()+sinkFactor, food.getPos().getZ());
    }

    @Override
    public void update() {
        IFoodState newState = checkState();
        if(newState.equals(this)){ 
            sink();
        }
        else{
            context.setState(newState);
        }
    }
}
