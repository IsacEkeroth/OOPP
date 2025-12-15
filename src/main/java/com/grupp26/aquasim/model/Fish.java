package com.grupp26.aquasim.model;

public class Fish implements IFish {

    // setup data
    private final IAquarium aquarium;
    private boolean isAlive;

    // fishdata
    private final IFishTypeData fishTypeData;
    private final IFishBehaviour behaviour;
    private final int baseSpeed;
    private final int bitingPower;

    // fiskinstansens stats
    private int age;
    private int health;
    private int hunger;
    private int speed;

    private Vec2<Integer> size = new Vec2<>(50, 50);
    private Vec3<Integer> pos = new Vec3<Integer>(640, 360, 1);

    public Fish(IAquarium aquarium, IFishTypeData fishTypeData, double initialDirection) {

        this.isAlive = true;
        this.aquarium = aquarium;
        this.fishTypeData = fishTypeData;
        this.behaviour = fishTypeData.createBehaviour(this, initialDirection);

        this.health = fishTypeData.getMaxHealth();
        this.baseSpeed = fishTypeData.getBaseSpeed();
        this.bitingPower = fishTypeData.getBitingPower();

        this.hunger = 0;
        this.age = 0;

    }

    @Override
    public IAquarium getAquarium() {
        return aquarium;
    }

    @Override
    public int getAge() {
        return age;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        // clamp, minst 0, max maxhealth
        this.health = Math.max(0, Math.min(fishTypeData.getMaxHealth(), health));
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = Math.max(0, Math.min(100, hunger));
    }

    public int getBaseSpeed() {
        return baseSpeed;
    }

    public Vec2<Integer> getSize() {
        return size;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public Vec3<Integer> getPos() {
        return pos;
    }

    @Override
    public int getBitingPower() {
        return bitingPower;
    }

    public boolean isAlive() {
        return isAlive;
    }

    @Override
    public void setPos(int x, int y, int z) {
        if (aquarium.isValidPosition(new Vec2<>(x, y), size)) {
            pos.setX(x);
            pos.setY(y);
            pos.setZ(z);
        } else {
            throw new IllegalArgumentException("Invalid position for fish");
        }
    }

    @Override
    public void tick() {
        age++;
        hunger++;

        if (hunger >= 100) {
            health--;
            hunger = 100; // clamp, max 100 hunger
        }

        if (health <= 0) {
            health = 0; // clamp, minst 0 hälsa
            this.isAlive = false;
        }

        speed = Math.max(1, baseSpeed + (hunger / 20));
        this.behaviour.update();

    }

    public double getDirection() {
        return behaviour.getDirection();
    }
}