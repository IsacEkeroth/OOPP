package com.grupp26.aquasim.model;

import java.util.ArrayList;
import java.util.List;

public class School {
    private static School instance;
    
    private List<IFish> members = new ArrayList<>();
    
    private School() {
    }
    
    public static School getInstance() {
        if (instance == null) {
            instance = new School();
        }
        return instance;
    }
    
    public void addMember(IFish fish) {
        if (!members.contains(fish)) {
            members.add(fish);
        }
    }
    
    public void removeMember(IFish fish) {
        members.remove(fish);
    }
    
    public List<IFish> getMembers() {
        return new ArrayList<>(members);
    }
    
    public static void reset() {
        getInstance().members.clear();
    }
}
