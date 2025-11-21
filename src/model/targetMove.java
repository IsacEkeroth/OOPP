package model;

public class targetMove implements IAi {

    private final IAquarium aquarium;
    private double direction;
    private Vec3 target;

    public targetMove(IAquarium aquarium, double direction){
        this.aquarium = aquarium;
        this.direction = direction;
        this.target = null;
    }
    
    @Override
    public void move(IFish fish){
    }

    public void setTarget(Vec3 target){
        this.target = target;
    }

    public Vec3 getTarget(){
        return target;
    }
}
