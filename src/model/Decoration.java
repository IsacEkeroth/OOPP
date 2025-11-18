package model;

import java.util.Vector;

public class Decoration implements IDecoration {

    private Vector<Integer> pos;

    @Override
    public void setPos(int x, int y, int z) {
        pos.set(0, x);
        pos.set(1, y);
        pos.set(2, z);
    }

}