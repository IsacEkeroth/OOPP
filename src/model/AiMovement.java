package model;

public class AiMovement implements IAi {

    private final IAquarium aquarium;
    private double direction;

    public AiMovement(IAquarium aquarium, double direction){
        this.aquarium = aquarium;
        this.direction = direction;
    }

    @Override
    public void move(IFish fish){
        Boolean isValid = aquarium.isValidPosition(fish.getPos(), fish.getSize());
        if(!isValid){
            double newDirection = (this.direction + Math.PI) % (2*Math.PI);
            // mirrors direction
            this.direction = newDirection;
        }
        int newX = (int) Math.ceil(fish.getPos().get(0) + (fish.getSpeed()*Math.cos(this.direction))); 
        int newY = (int) Math.ceil(fish.getPos().get(1) + (fish.getSpeed()*Math.sin(this.direction))); 
        // newPos = oldPos + Speed*direction
        // M.ceil förhindrar ingen rörelse alls
        fish.setPos(newX,newY,0);
    };
}