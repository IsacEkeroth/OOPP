package model;

public class simpleMove implements IAi {

    private final IAquarium aquarium;
    private double direction;

    public simpleMove(IAquarium aquarium, double direction){
        this.aquarium = aquarium;
        this.direction = direction;
    }

    @Override
    public void move(IFish fish){
        int newX = fish.getPos().getX();
        int newY = fish.getPos().getY();
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
        // if statements to prevent 0 movement by rounding movement backwards:down and forwards:up


        Vec3<Integer>  newPos = new Vec3<Integer>(newX, newY, fish.getPos().getZ());

        // Boolean isValid = aquarium.isValidPosition(newPos, fish.getSize());
        Boolean isValid = true;
        if(!isValid){
            double newDirection = (this.direction + Math.PI) % (2*Math.PI);
            // mirrors direction
            this.direction = newDirection;

            momentumX = (fish.getSpeed()*Math.cos(this.direction));
            momentumY = (fish.getSpeed()*Math.sin(this.direction));

            if (momentumX >= 0){
                newX = (int) Math.ceil(fish.getPos().getX() + momentumX);
            }
            else{
                newX = (int) Math.floor(fish.getPos().getX() + momentumX);
            }
            if (momentumY >= 0){
                newY = (int) Math.ceil(fish.getPos().getY() + momentumY);
            }
            else{
                newY = (int) Math.floor(fish.getPos().getY() + momentumY);
            }
            // new mirrored direction
        }
        fish.setPos(newX,newY,fish.getPos().getZ());
    }
}