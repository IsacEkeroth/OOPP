package model;
import java.util.Vector;

public class AiMovement implements IAi {

    private final IAquarium aquarium;
    private double direction;

    public AiMovement(IAquarium aquarium, double direction){
        this.aquarium = aquarium;
        this.direction = direction;
    }

    @Override
    public void move(IFish fish){
        int newX = (int) Math.ceil(fish.getPos().get(0) + (fish.getSpeed()*Math.cos(this.direction))); 
        int newY = (int) Math.ceil(fish.getPos().get(1) + (fish.getSpeed()*Math.sin(this.direction)));
        // newPos = oldPos + Speed*direction
        // M.ceil förhindrar ingen rörelse alls

        Vector<Integer>  newPos = new Vector<>(); newPos.set(0,newX); newPos.set(1,newY);

        Boolean isValid = aquarium.isValidPosition(newPos, fish.getSize());
        if(!isValid){
            double newDirection = (this.direction + Math.PI) % (2*Math.PI);
            // mirrors direction
            this.direction = newDirection;

            newX = (int) Math.ceil(fish.getPos().get(0) + (fish.getSpeed()*Math.cos(this.direction))); 
            newY = (int) Math.ceil(fish.getPos().get(1) + (fish.getSpeed()*Math.sin(this.direction)));
            // new mirrored direction
        }
        fish.setPos(newX,newY,fish.getPos().get(2));
    }

    public void targetMove(IFish fish){
        // implement
    }

}