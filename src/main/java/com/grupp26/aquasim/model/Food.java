package com.grupp26.aquasim.model;

/**
 * Represents a eatable entity in the aquarium.
 * <p>
 *     The class handles the foods value and its state (isEaten). <br>
 *     Just like the fish, a food delegates its movements or logic to a {@link IFoodBehaviour}
 *     and works as a resource that the fish can interact with.
 * </p>
 */
public class Food implements IEdible {
    private Vec2<Integer> size = new Vec2<Integer>(50, 50);
    private Vec3<Integer> pos;
    private int amount;
    private boolean isEaten;
    private IFoodBehaviour behaviour;
    private IAquarium aquarium;

    private String TYPE = "FOOD";

    public Food(Vec3<Integer> pos, int amount, IAquarium aquarium) {
        this.pos = pos;
        this.amount = amount;
        this.isEaten = amount == 0;
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
        isEaten = (this.amount == 0);
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public void eatenBy(IFish fish) {
        int nutritionGained = Math.min(amount, fish.getBitingPower());
        setAmount(amount - fish.getBitingPower());
        fish.setHunger(fish.getHunger() - nutritionGained);
    }

    @Override
    public boolean isEaten() {
        return isEaten;
    }

    @Override
    public void tick() {
        behaviour.update();
    }

    @Override
    public Vec2<Integer> getSize() {
        return new Vec2<>(size);
    }

    @Override
    public String getType() {
        return TYPE;
    }

}
