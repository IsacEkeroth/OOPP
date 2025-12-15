package com.grupp26.aquasim.model;




public class UniqueID {

    private UniqueID() {
    }

    public static String createUniqueID() {
        return java.util.UUID.randomUUID().toString();
    }
}
