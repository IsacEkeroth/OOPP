package com.grupp26.aquasim.model;


/**
 * A utility class for generating globally unique identifiers.
 * <p>
 *     This class provides a centralized method for creating unique String-IDs
 *     that can be used to differentiate between entities in the simulation.
 * </p>
 */

public class UniqueID {

    private UniqueID() {
    }

    public static String createUniqueID() {
        return java.util.UUID.randomUUID().toString();
    }
}
