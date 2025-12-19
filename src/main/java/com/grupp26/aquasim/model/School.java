package com.grupp26.aquasim.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton class representing a school of fish.
 * <p>
 *     This class manages the members of the school, allowing fish to be added or removed,
 *     and provides access to the current list of members.
 * </p>
 */
public class School {
    private static School instance;
    
    private List<IFish> members = new ArrayList<>();
    
    private School() {}
    
    /**
     * Returns the singleton instance of the School.
     *
     * @return the singleton School instance
     */
    public static School getInstance() {
        if (instance == null) {
            instance = new School();
        }
        return instance;
    }
    
    /**
     * Adds a fish to the school if it is not already a member.
     *
     * @param fish the fish to add
     */
    public void addMember(IFish fish) {
        if (!members.contains(fish)) {
            members.add(fish);
        }
    }
    
    /**
     * Removes a fish from the school.
     *
     * @param fish the fish to remove
     */
    public void removeMember(IFish fish) {
        members.remove(fish);
    }
    
    /**
     * Returns a list of the current members of the school.
     *
     * @return a list of fish in the school
     */
    public List<IFish> getMembers() {
        return new ArrayList<>(members);
    }
    
    /**
     * Resets the school by clearing all members.
     * <p>
     *     This method is useful for ensuring a fresh state, for example when creating a new aquarium model.
     * </p>
     */
    public static void reset() {
        getInstance().members.clear();
    }
}
