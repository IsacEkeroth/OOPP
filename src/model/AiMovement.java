package model;

public class AiMovement implements IAi {

    private final IAquarium aquarium;

    public AiMovement(IAquarium aquarium){
        this.aquarium = aquarium;
    }

    @Override
    public void move(IFish fish){
        Boolean isValid = (fish.getPos()+1).isValidPosition();
        if(!isValid){
            double newDirection = Math.mod(fish.getDirection() + Math.PI) % (2*Math.PI);
            // mirrors direction
            fish.setDirection(newDirection);
        }
        int newX = (int) Math.ceil(fish.getPos().get(0) + (fish.getSpeed()*Math.cos(fish.getDirection()))); 
        int newY = (int) Math.ceil(fish.getPos().get(1) + (fish.getSpeed()*Math.sin(fish.getDirection()))); 
        // newPos = oldPos + Speed*direction
        // M.ceil förhindrar ingen rörelse alls
        fish.setPos(newX,newY,0);
    };
}