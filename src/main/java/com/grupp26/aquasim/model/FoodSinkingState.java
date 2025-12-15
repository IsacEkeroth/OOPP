package com.grupp26.aquasim.model;

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
        return (food.getPos().getY() > aquarium.getAquariumSize().getY()-50);
    }

    private IFoodState checkState(){
        if (food.isEaten()){
            return context.getEatenState();
        }
        else if (hasReachedBottom()){ 
            //har "nått" botten
            //valde 50 för att det såg ut som botten på min skärm när jag testade det
            return context.getStaleState();
        }
        else{
            return context.getSinkingState();
        }
    }

    private void sink(){
        //tänker att food i nuläget sjunker snabbare med högre amount (mer mat=tyngre)
        //kan inte använda simpleMove eller targetMove för att du tar in en IFish och kräver en getSpeed()
        int sinkFactor = Math.max(1,food.getAmount()/100);
        food.setPos(food.getPos().getX(), food.getPos().getY()+sinkFactor, food.getPos().getZ());
    }

    @Override
    public void update(){
        IFoodState newState = checkState();
        if(newState.equals(this)){ 
            sink();
        }
        else{
            context.setState(newState);
        }
    }
}
