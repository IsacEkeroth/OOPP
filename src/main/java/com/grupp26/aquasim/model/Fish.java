package com.grupp26.aquasim.model;

public class Fish implements IFish {

    private final IAquarium aquarium;

    private boolean isAlive;

    private int age;
    private int health;
    private int hunger;
    private int baseSpeed;
    private int speed;

    private Vec2<Integer> size = new Vec2<>(50, 50);
    private Vec3<Integer> pos;
    private IBehaviour behaviour;;

    public Fish(IAquarium aquarium) {

        this.isAlive = true;
        this.aquarium = aquarium;

        this.health = 100;
        this.hunger = 0;
        this.age = 0;
        this.baseSpeed = 5;
        this.speed = baseSpeed;
        this.behaviour = new GoldFishBehaviour(this, 0);

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
        // clamp, minst 0, max 100
        this.health = Math.max(0, Math.min(100, health));
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

    public void setBaseSpeed(int baseSpeed) {
        this.baseSpeed = Math.max(0, baseSpeed);
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
    public void setPos(int x, int y, int z) {
        pos.setX(x);
        pos.setY(y);
        pos.setZ(z);
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

        speed = Math.max(1, baseSpeed - (hunger / 20));

        this.behaviour.update();
        
    }
}