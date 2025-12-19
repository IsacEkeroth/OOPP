package com.grupp26.aquasim.model;

import java.util.List;

/**
 * Movement behavior for fish that swim in a school.
 * <p>
 *     This class implements schooling behavior, where fish adjust their direction
 *     based on the center of the school, avoid walls, and exhibit slight random
 *     wiggle when inside the school.
 * </p>
 */
public class SchoolMove implements IMovement{
    private final IAquarium aquarium;
    private double direction;
    
    // Schooling
    private final double schoolRadius = 100.0;
    private final double turnFactor = 0.2; // how sharp the fish turns towards the school center
                                          // (0 = no turn, 1 = instant turn)
    
    // Wiggle
    private final double wiggleAmplitude = 0.1; // how sharp random turns while inside school
    
    // Wall avoidance
    int wallMargin = 50; // how close to the wall before avoidance starts
    double wallTurnFactor = 0.2; // how sharp the fish turns away from walls
                                // (0 = no turn, 1 = instant turn)
    public SchoolMove(IAquarium aquarium, double direction) {
        this.aquarium = aquarium;
        this.direction = direction;
    }
    
    /**
     * Calculates and updates the fish's position based on schooling behavior.
     *
     * @param fish The fish to be moved.
     */
    @Override
    public void move(IFish fish) {
        // Deciding direction based on school center
        Vec2<Double> schoolCenter = getSchoolCenter(fish);
        double dx = schoolCenter.getX() - fish.getPos().getX();
        double dy = schoolCenter.getY() - fish.getPos().getY();
        double distanceToCenter = Math.hypot(dx,dy);
        
        // Smooth turn towards school center
        if (distanceToCenter > schoolRadius) {
            double angleToCenter = Math.atan2(dy, dx);
            direction = shortestAnglePath(direction, angleToCenter, turnFactor);
        } else {
            // Random wiggle when inside school
            direction += (Math.random() - 0.5) * wiggleAmplitude;
        }
        
        // Wall avoidance
        double xPush = 0.0;
        double yPush = 0.0;
        
        // Left wall turn right (0 rad)
        if (fish.getPos().getX() < wallMargin) {
            xPush = 1.0;
        }
        else if (fish.getPos().getX() + fish.getSize().getX() > aquarium.getAquariumSize().getX() - wallMargin) {
            xPush = -1.0;
        }
        // Top wall turn down (PI/2 rad)
        if (fish.getPos().getY() < wallMargin) {
            yPush = 1.0;
        } // Bottom wall turn up (-PI/2 rad)
        else if (fish.getPos().getY() + fish.getSize().getY() > aquarium.getAquariumSize().getY() - wallMargin) {
            yPush = -1.0;
        }
        
        if (xPush != 0.0 || yPush != 0.0) {
            double wallAvoidAngle = Math.atan2(yPush, xPush);
            direction = shortestAnglePath(direction, wallAvoidAngle, wallTurnFactor);
        }
        
        // Actual movement
        int moveX = (int) (Math.cos(direction) * fish.getSpeed());
        int moveY = (int) (Math.sin(direction) * fish.getSpeed());
        fish.setPos(fish.getPos().getX() + moveX, fish.getPos().getY() + moveY, fish.getPos().getZ());
    }
    
    // Makes sure turns are done via the shortest angle path, from 'from' to 'to'
    // turnFactor determines how much of the turn is completed (0 = no turn, 1 = instant turn)
    private double shortestAnglePath(double from, double to, double turnFactor) {
        double diff = to - from;
        // if diff < -180 degrees, add 360 to take the shorter path
        while (diff < -Math.PI) diff += 2 * Math.PI;
        // if diff > 180 degrees, subtract 360 to take the shorter path
        while (diff > Math.PI) diff -= 2 * Math.PI;
        return from + diff * turnFactor;
    }
    
    // Calculate the center position of the school
    private Vec2<Double> getSchoolCenter(IFish fish) {
        List<IFish> schoolMembers = School.getInstance().getMembers();
        
        double totalX = 0.0;
        double totalY = 0.0;
        int count = 0;
        for (IFish member : schoolMembers) {
            if (member != fish && member.isAlive()) {
                totalX += member.getPos().getX();
                totalY += member.getPos().getY();
                count++;
            }
        }
        if (count == 0) {
            return new Vec2<>(fish.getPos().getX().doubleValue(), fish.getPos().getY().doubleValue());
        }
        return new Vec2<>(totalX / count, totalY / count);
    }
    
    /**
     * Gets the current direction of the fish.
     *
     * @return The direction in radians.
     */
    @Override
    public double getDirection(){
        return this.direction;
    }
    /**
     * Sets the current direction of the fish.
     *
     * @param direction The new direction in radians.
     */
    @Override
    public void setDirection(double direction){
        this.direction = direction;
    }
}
