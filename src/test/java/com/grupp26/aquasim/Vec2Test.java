/*package com.grupp26.aquasim;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.grupp26.aquasim.model.Vec2;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class Vec2Test {
    private Vec2<Integer> vec2;

    @BeforeEach
    public void setup() {
        vec2 = new Vec2<Integer>(null, null);
    }

    @Test
    public void testInitialValues() {
        assertNull(vec2.getX());
        assertNull(vec2.getY());
    }

    @Test
    public void testSetAndGetX() {
        vec2.setX(5);
        assertEquals(5, vec2.getX());
    }

    @Test
    public void testSetAndGetY() {
        vec2.setY(10);
        assertEquals(10, vec2.getY());
    }

    @ParameterizedTest
    @MethodSource("provideVec2Values")
    public void testVec2Parameterized(Integer x, Integer y) {
        Vec2<Integer> v = new Vec2<>(x, y);
        assertEquals(x, v.getX());
        assertEquals(y, v.getY());
    }

    static Stream<org.junit.jupiter.params.provider.Arguments> provideVec2Values() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of(1, 2),
                org.junit.jupiter.params.provider.Arguments.of(-5, 100),
                org.junit.jupiter.params.provider.Arguments.of(null, 0),
                org.junit.jupiter.params.provider.Arguments.of(42, null));
    }
}

 */
