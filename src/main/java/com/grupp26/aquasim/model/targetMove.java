package com.grupp26.aquasim.model;

public class targetMove implements IAi {

    private final IAquarium aquarium;
    private double direction;
    private Vec3<Integer> target;

    public targetMove(IAquarium aquarium, double direction){
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
        int yaxis = target.getY() - fish.getPos().getY();
        
        double length = Math.sqrt(xaxis*xaxis + yaxis*yaxis);
        
        if (length <= fish.getSpeed()) {
            fish.setPos(target.getX(), target.getY(), target.getZ());
            return;
        }

        double stepX = (xaxis / length) * fish.getSpeed();
        double stepY = (yaxis / length) * fish.getSpeed();

        int newX = fish.getPos().getX() + (int)Math.round(stepX);
        int newY = fish.getPos().getY() + (int)Math.round(stepY);

        double newDirection = Math.atan2(yaxis, xaxis);
        this.direction = newDirection;
        fish.setPos(newX, newY, fish.getPos().getZ());
    }

    public void setTarget(Vec3<Integer> targetPos){
        this.target = targetPos;
    }

    public double getDirection(){
        return this.direction;
    }

}
