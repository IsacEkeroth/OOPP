package com.grupp26.aquasim.model;

public class FoodStaleState implements IFoodState {
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
