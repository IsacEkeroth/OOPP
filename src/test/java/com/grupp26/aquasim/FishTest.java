/*package com.grupp26.aquasim;

import com.grupp26.aquasim.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FishTest {

    private IAquarium aquarium;
    private FishFactory fishFactory;
    private Fish fish;

    @BeforeEach
    public void setup() {
        aquarium = new Aquarium();
        fishFactory = new FishFactory();

        fish = fishFactory.createGoldfish(aquarium, 0);
    }

    @Test
    public void testAquariumIsSet() {
        assertEquals(aquarium, fish.getAquarium());
    }

    @Test
    public void testSetAndGetHunger() {
        fish.setHunger(40);
        assertEquals(40, fish.getHunger());

        fish.setHunger(150);
        assertEquals(100, fish.getHunger());

        fish.setHunger(-5);
        assertEquals(0, fish.getHunger());
    }

    @Test
    public void testSetAndGetHealth() {
        fish.setHealth(90);
        assertEquals(90, fish.getHealth());

        fish.setHealth(150);
        assertEquals(100, fish.getHealth());

        fish.setHealth(-20);
        assertEquals(0, fish.getHealth());
    }

    @Test
    public void testTickIncreasesHunger() {
        fish.setHunger(20);

        fish.tick();
        assertEquals(21, fish.getHunger());

        fish.tick();
        fish.tick();
        assertEquals(23, fish.getHunger());
    }

    @Test
    public void testHealthDecreasesAtHighHunger() {
        fish.setHunger(100);
        fish.setHealth(100);

        fish.tick();

        assertEquals(99, fish.getHealth());
    }

    @Test
    public void testFishStartsAlive() {
        assertTrue(fish.isAlive());
    }

    @Test
    public void testFishDiesAtZeroHealth() {
        fish.setHealth(1);
        fish.setHunger(100);

        fish.tick();

        assertFalse(fish.isAlive());
        assertEquals(0, fish.getHealth());
    }

}

 */
