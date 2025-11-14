package model;

public class targetMove implements IAi {

    private final IAquarium aquarium;
    private double direction;

    public targetMove(IAquarium aquarium, double direction){
        this.aquarium = aquarium;
        this.direction = direction;
    }


    @Override
    public void move(IFish fish){
    // goal is to find shortest path to coordinates
    // check pos() at every call
    }
}
