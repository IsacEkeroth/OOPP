package com.grupp26.aquasim;

import com.grupp26.aquasim.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AquariumTest {
    private Aquarium aquarium;

    private FishFactory fishFactory;
    private DecorationFactory decorationFactory; // = new DecorationFactory(aquarium);
    private FoodFactory foodFactory; // = new FoodFactory(aquarium);

    private final int width = 800;
    private final int height = 600;
    private final int HEIGHT_OFFSET = 25; // must correspond to offset in Aquarium class

    @BeforeEach
    public void setup() {
        aquarium = new Aquarium(width, height + HEIGHT_OFFSET);
    }

    @Test
    public void testAquariumSize() {
        assert aquarium.getAquariumSize().getX() == width;
        assert aquarium.getAquariumSize().getY() == height;
    }

    @Test
    public void testValidPosition() {
        Vec2<Integer> objSize = new Vec2<>(50, 50);

        // Exact top-left corner
        assertTrue(aquarium.isValidPosition(new Vec2<>(0, 0), objSize));

        // Exact bottom-right corner
        Vec2<Integer> maxPos = new Vec2<>(width - objSize.getX(), height - objSize.getY());
        assertTrue(aquarium.isValidPosition(maxPos, objSize));

        // Test middle
        Vec2<Integer> midPos = new Vec2<>(width / 2, height / 2);
        assertTrue(aquarium.isValidPosition(midPos, objSize));
    }

    @Test
    public void testInvalidPosition() {
        Vec2<Integer> objSize = new Vec2<>(50, 50);

        // Negative X
        assertFalse(aquarium.isValidPosition(new Vec2<>(-1, 0), objSize));

        // Negative Y
        assertFalse(aquarium.isValidPosition(new Vec2<>(0, -1), objSize));

        // Exceeding width
        assertFalse(aquarium.isValidPosition(new Vec2<>(width - objSize.getX() + 1, 100), objSize));

        // Exceeding height
        assertFalse(aquarium.isValidPosition(new Vec2<>(100, height - objSize.getY() + 1), objSize));

        // Completely outside
        assertFalse(aquarium.isValidPosition(new Vec2<>(width + 10, height + 10), objSize));
    }

    // Add clampTest when implemented
    @Test
    public void testClampedPosition() {
        Vec2<Integer> objSize = new Vec2<>(50, 50);

        // Negative clamps to 0
        Vec2<Integer> negPos = aquarium.clampPosition(new Vec2<>(-10, -10), objSize);
        assertEquals(0, negPos.getX());
        assertEquals(0, negPos.getY());

        // Exceeding aquarium size clamps to aquariumsize - objSize
        Vec2<Integer> exceedPos = aquarium.clampPosition(new Vec2<>(width + 10, height + 10), objSize);
        assertEquals(width - objSize.getX(), exceedPos.getX());
        assertEquals(height - objSize.getY(), exceedPos.getY());

        // Inside aquarium remains unchanged
        Vec2<Integer> insidePos = aquarium.clampPosition(new Vec2<>(width / 2, height / 2), objSize);
        assertEquals(width / 2, insidePos.getX());
        assertEquals(height / 2, insidePos.getY());
    }

    @Test
    public void testAddRemoveFish() {
        fishFactory = new FishFactory(aquarium);

        // Empty when initialized
        assertEquals(0, aquarium.getState().getFish().size()); // Add a getFish in Aquarium?

        IFish fish1 = fishFactory.createGoldfish(0);
        IFish fish2 = fishFactory.createGoldfish(0);
        assertNotNull(fish1); // More of a factory test?

        // Test adding fish
        aquarium.addFish(fish1);
        assertEquals(1, aquarium.getState().getFish().size());
        assertTrue(aquarium.getState().getFish().contains(fish1));

        // Test removing non-added fish
        aquarium.removeFish(fish2);
        assertEquals(1, aquarium.getState().getFish().size());

        // Test removing added fish
        aquarium.removeFish(fish1);
        assertEquals(0, aquarium.getState().getFish().size());

        // Test removing last fish
        aquarium.addFish(fish1);
        aquarium.addFish(fish2);
        assertEquals(2, aquarium.getState().getFish().size());
        aquarium.removeLastFish();
        assertEquals(1, aquarium.getState().getFish().size());
        assertTrue(aquarium.getState().getFish().contains(fish1));
    }

    @Test
    public void testAddDecoration() {
        decorationFactory = new DecorationFactory(aquarium);

        // Empty when initialized
        assertEquals(0, aquarium.getState().getDecorations().size());

        IDecoration deco1 = decorationFactory.createDecoration("seaweed");
        IDecoration deco2 = decorationFactory.createDecoration("anchor");
        assertNotNull(deco1);
        assertNotNull(deco2);

        // Test adding decorations
        aquarium.addDecoration(deco1);
        assertEquals(1, aquarium.getState().getDecorations().size());
        assertTrue(aquarium.getState().getDecorations().contains(deco1));
        assertFalse(aquarium.getState().getDecorations().contains(deco2));
    }

    @Test
    public void testAddFood() {
        foodFactory = new FoodFactory(aquarium);

        // Empty when initialized
        assertEquals(0, aquarium.getState().getFood().size());

        IEdible food1 = foodFactory.createFood("base");
        IEdible food2 = foodFactory.createFood("base");
        assertNotNull(food1);

        // Test adding food
        aquarium.addFood(food1);
        assertEquals(1, aquarium.getFood().size());
        assertTrue(aquarium.getFood().contains(food1));
        assertFalse(aquarium.getFood().contains(food2));
    }

    @Test
    public void testEnvironmentAttributes() {
        // Temperature (0 - 100)
        aquarium.setTemperature(30);
        assertEquals(30, aquarium.getTemperature());
        aquarium.setTemperature(150);
        assertEquals(100, aquarium.getTemperature());
        aquarium.setTemperature(-10);
        assertEquals(0, aquarium.getTemperature());

        // Algae (0 - 100)
        aquarium.setAlgaeLevel(20);
        assertEquals(20, aquarium.getAlgaeLevel());
        aquarium.setAlgaeLevel(101);
        assertEquals(100, aquarium.getAlgaeLevel());
        aquarium.setAlgaeLevel(-1);
        assertEquals(0, aquarium.getAlgaeLevel());

        // O2 Concentration (0 - no upper limit)
        aquarium.setO2Conc(150);
        assertEquals(150, aquarium.getO2Conc());
        aquarium.setO2Conc(-30);
        assertEquals(0, aquarium.getO2Conc());

        // Salinity (0 - 340)
        aquarium.setSalinity(50);
        assertEquals(50, aquarium.getSalinity());
        aquarium.setSalinity(500);
        assertEquals(340, aquarium.getSalinity());
        aquarium.setSalinity(-20);
        assertEquals(0, aquarium.getSalinity());

        // pH Level (0 - 140)
        aquarium.setPHLevel(70);
        assertEquals(70, aquarium.getPHLevel());
        aquarium.setPHLevel(200);
        assertEquals(140, aquarium.getPHLevel());
        aquarium.setPHLevel(-5);
        assertEquals(0, aquarium.getPHLevel());
    }

    @Test
    public void testTick() {
        fishFactory = new FishFactory(aquarium);
        decorationFactory = new DecorationFactory(aquarium);
        foodFactory = new FoodFactory(aquarium);

        IFish fish = fishFactory.createGoldfish(0);
        IDecoration tickDeco = decorationFactory.createDecoration("seaweed");
        IDecoration noTickDeco = decorationFactory.createDecoration("anchor");
        IEdible food = foodFactory.createFood("base");

        food.setPos(width / 2, 0, 0);
        aquarium.setAlgaeLevel(0);

        // Tickables not ticked when not added in aquarium
        aquarium.tick();
        assertEquals(0, fish.getAge());
        assertEquals(0, aquarium.getAlgaeLevel());
        assertEquals(0, food.getPos().getY());

        aquarium.addFish(fish);
        aquarium.addDecoration(tickDeco);
        aquarium.addDecoration(noTickDeco);
        aquarium.addFood(food);

        // Tickable ticked when added
        aquarium.tick();
        assertEquals(1, fish.getAge());
        assertEquals(1, aquarium.getAlgaeLevel());
        assertTrue(food.getPos().getY() > 0);
    }
}