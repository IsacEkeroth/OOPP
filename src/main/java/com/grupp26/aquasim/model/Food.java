package com.grupp26.aquasim.model;

public class Food implements IEdible {

    private Vec3<Integer> pos;
    private int amount;
    private boolean isEaten;

    public Food(Vec3<Integer> pos, int amount) {
        this.pos = pos;
        this.amount = amount;
        this.isEaten = false;
    }

    @Override
    public void setPos(int x, int y, int z) {
        this.pos.setX(x);
        this.pos.setY(y);
        this.pos.setZ(z);
    }

    @Override
    public Vec3<Integer> getPos() { return pos; }

    @Override
    public void setAmount(int amount) {
        this.amount = Math.max(0, amount);
        this.isEaten = (this.amount == 0);
    }

    @Override
    public int getAmount() { return amount; }

    @Override
    public void eat(int amount) {

        if (isEaten || amount <= 0) return;

        this.amount -= amount;

        if (this.amount <= 0) {
            this.amount = 0;
            this.isEaten = true;
        }

    }

    @Override
    public boolean isEaten() { return isEaten; }

}
