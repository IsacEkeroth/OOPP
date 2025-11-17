package model;


public interface IAquarium {
    // comment = private attributes
    // List<IFish> fish;
    // Point aquariumSize = new Point(720, 540);

    void addFish(IFish fish);

    void removeFish(IFish fish);

    boolean isValidPosition(Vec2<Integer> pos, Vec2<Integer> size);
    
    Vec2 getAquariumSize();
}
