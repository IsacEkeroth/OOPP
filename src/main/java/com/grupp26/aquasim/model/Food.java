package com.grupp26.aquasim.model;

public class Food implements IEdible {
    private Vec2<Integer> size = new Vec2<Integer>(50, 50);
    private Vec3<Integer> pos;
    private int amount;
    private boolean isEaten;
    private IFoodBehaviour behaviour;
    private IAquarium aquarium;

    public Food(Vec3<Integer> pos, int amount, IAquarium aquarium) {
        this.pos = pos;
        this.amount = amount;
        this.isEaten = false;
        this.behaviour = new FoodBehaviour(this, aquarium);
        this.aquarium = aquarium;
    }

    @Override
    public void setPos(int x, int y, int z) {
        Vec2<Integer> clampedPos = aquarium.clampPosition(new Vec2<Integer>(x, y), size);
        pos.setX(clampedPos.getX());
        pos.setY(clampedPos.getY());
        pos.setZ(z);
    }

    @Override
    public Vec3<Integer> getPos() {
        return pos;
    }

    public void setAmount(int amount) {
        this.amount = Math.max(0, amount);
        this.isEaten = (this.amount == 0);
    }

    public int getAmount() {
        return amount;
    }

    public void eat(int amount) {

        if (isEaten || amount <= 0)
            return;

        this.amount -= amount;

        if (this.amount <= 0) {
            this.amount = 0;
            this.isEaten = true;
        }

    }

    public boolean isEaten() {
        return isEaten;
    }

    @Override
    public void tick() {
        behaviour.update();
    }

    @Override
    public Vec2<Integer> getSize() {
        return new Vec2<Integer>(size);
    }

}
