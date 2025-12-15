package com.grupp26.aquasim.model;

public class TargetMove implements IMovement {

    private final IAquarium aquarium;
    private double direction;
    private Vec3<Integer> target;

    public TargetMove(IAquarium aquarium, double direction){
        this.aquarium = aquarium;
        this.direction = direction;
        this.target = null;
    }
    
    @Override
    public void move(IFish fish){
        if (target == null){
            return;
        }

        int xaxis = target.getX() - fish.getPos().getX();
        //bottom of the triangle
        int yaxis = target.getY() - fish.getPos().getY();
        //side  of the triangle
        
        double length = Math.sqrt(xaxis*xaxis + yaxis*yaxis);
        //hypothenus
        
        if (length <= fish.getSpeed()) {
            fish.setPos(target.getX(), target.getY(), target.getZ());
            return;
            //prevents fish from overshooting and orbiting
        }

        double stepX = (xaxis / length) * fish.getSpeed();
        double stepY = (yaxis / length) * fish.getSpeed();
        //calculate movement

        int newX = fish.getPos().getX() + (int)Math.round(stepX);
        int newY = fish.getPos().getY() + (int)Math.round(stepY);
        Vec2<Integer> newPos = new Vec2<Integer>(newX, newY);
        //new position

        Boolean isValid = aquarium.isValidPosition(newPos, fish.getSize());
        if(!isValid){
            newX = fish.getPos().getX();
            newY = fish.getPos().getY();
            // if not valid position, stay? (you cant really turn around)
        }

        double newDirection = Math.atan2(yaxis, xaxis);
        this.direction = newDirection;
        //change direction

        fish.setPos(newX, newY, fish.getPos().getZ());
    }

    public void setTarget(Vec3<Integer> targetPos){
        this.target = targetPos;
    }

    public Vec3<Integer> getTarget(){
        return this.target;
    }

    @Override
    public double getDirection(){
        return this.direction;
    }

    @Override 
    public void setDirection(double direction){
        this.direction = direction;
    }

}
