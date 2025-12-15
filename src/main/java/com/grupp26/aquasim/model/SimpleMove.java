package com.grupp26.aquasim.model;

public class SimpleMove implements IMovement {

    private final IAquarium aquarium;
    private double direction;
    private double wigglePhase;
    private final double wiggleAmplitude;
    private final double wiggleSpeed;

    public SimpleMove(IAquarium aquarium, double direction) {
        this.aquarium = aquarium;
        this.direction = direction;

        this.wigglePhase = Math.random() * 2 * Math.PI;
        this.wiggleAmplitude = 0.1 + Math.random() * 0.1;
        this.wiggleSpeed = 0.01 + Math.random() * 0.01;
    }

    @Override
    public void move(IFish fish) {
        wigglePhase += wiggleSpeed;
        double effectiveDirection = direction + Math.sin(wigglePhase) * wiggleAmplitude;
        // Phase indikerar vart i vågen vi befinner oss, 
        // Amplitude hur kraftig wigglen är,
        // Speed hur snabbt en svängning sker

        int newX = fish.getPos().getX();
        int newY = fish.getPos().getY();
        double momentumX = (fish.getSpeed() * Math.cos(effectiveDirection));
        double momentumY = (fish.getSpeed() * Math.sin(effectiveDirection));

        if (momentumX >= 0) {
            newX = (int) Math.ceil(newX + momentumX);
        } else {
            newX = (int) Math.floor(newX + momentumX);
        }
        if (momentumY >= 0) {
            newY = (int) Math.ceil(newY + momentumY);
        } else {
            newY = (int) Math.floor(newY + momentumY);
        }
        // newPos = oldPos + Speed*direction
        // if statements to prevent 0 movement by rounding movement backwards:down and
        // forwards:up

        Vec2<Integer> newPos = new Vec2<Integer>(newX, newY);

        Boolean isValid = aquarium.isValidPosition(newPos, fish.getSize());
        // Boolean isValid = true;

        if (!isValid) {

            boolean hitX = !aquarium.isValidPosition(new Vec2<>(newX, fish.getPos().getY()), fish.getSize());

            boolean hitY = !aquarium.isValidPosition(new Vec2<>(fish.getPos().getX(), newY), fish.getSize());

            // Reflektera rörelsevektorn
            if (hitX) {
                momentumX = -momentumX;
            }
            if (hitY) {
                momentumY = -momentumY;
            }

            // Uppdatera riktning
            this.direction = Math.atan2(momentumY, momentumX);

            // Räkna om position
            if (momentumX >= 0) {
                newX = (int) Math.ceil(fish.getPos().getX() + momentumX);
            } else {
                newX = (int) Math.floor(fish.getPos().getX() + momentumX);
            }

            if (momentumY >= 0) {
                newY = (int) Math.ceil(fish.getPos().getY() + momentumY);
            } else {
                newY = (int) Math.floor(fish.getPos().getY() + momentumY);
            }
        }
        fish.setPos(newX, newY, fish.getPos().getZ());
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
