package model;
import java.util.Vector;

public class simpleMove implements IAi {

    private final IAquarium aquarium;
    private double direction;

    public simpleMove(IAquarium aquarium, double direction){
        this.aquarium = aquarium;
        this.direction = direction;
    }

    @Override
    public void move(IFish fish){
        int newX = fish.getPos().get(0);
        int newY = fish.getPos().get(1);
        double momentumX = (fish.getSpeed()*Math.cos(this.direction));
        double momentumY = (fish.getSpeed()*Math.sin(this.direction));

        if (momentumX >= 0){
            newX = (int) Math.ceil(newX + momentumX); 
        }
        else{
            newX = (int) Math.floor(newX + momentumX); 
        }
        if (momentumY >= 0){
            newY = (int) Math.ceil(newY + momentumY);
        }
        else{
            newY = (int) Math.floor(newY + momentumY);
        }
        // newPos = oldPos + Speed*direction
        // if statements to prevent 0 movement by rounding movement backwards down and forwards up


        Vector<Integer>  newPos = new Vector<>(); newPos.add(newX); newPos.add(newY);

        Boolean isValid = aquarium.isValidPosition(newPos, fish.getSize());
        if(!isValid){
            double newDirection = (this.direction + Math.PI) % (2*Math.PI);
            // mirrors direction
            this.direction = newDirection;

            momentumX = (fish.getSpeed()*Math.cos(this.direction));
            momentumY = (fish.getSpeed()*Math.sin(this.direction));

            if (momentumX >= 0){
                newX = (int) Math.ceil(fish.getPos().get(0) + momentumX); 
            }
            else{
                newX = (int) Math.floor(fish.getPos().get(0) + momentumX); 
            }
            if (momentumY >= 0){
                newY = (int) Math.ceil(fish.getPos().get(1) + momentumY);
            }
            else{
                newY = (int) Math.floor(fish.getPos().get(1) + momentumY);
            }
            // new mirrored direction
        }
        fish.setPos(newX,newY,fish.getPos().get(2));
    }
}