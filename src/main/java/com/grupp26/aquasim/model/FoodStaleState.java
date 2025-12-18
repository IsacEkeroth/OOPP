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

    public FoodStaleState(IFoodBehaviour context, IEdible food, IAquarium aquarium){
        this.context = context;
        this.food = food;
        this.aquarium = aquarium;
    }

    private boolean hasReachedBottom(){
        return (food.getPos().getY() > aquarium.getAquariumSize().getY()-50);
    }

    // TODO     -- För utökning av food-states, ska vi lägga checkState i FoodBehaviours update() istället för att minska kodduplicering? --
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
