package com.grupp26.aquasim;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.grupp26.aquasim.model.Vec3;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class Vec3Test {
    private Vec3<Integer> vec3;

    @BeforeEach
    public void setup() {
        vec3 = new Vec3<Integer>(null, null, null);
    }

    @Test
    public void testInitialValues() {
        assertNull(vec3.getX());
        assertNull(vec3.getY());
        assertNull(vec3.getZ());
    }

    @Test
    public void testSetAndGetX() {
        vec3.setX(5);
        assertEquals(5, vec3.getX());
    }

    @Test
    public void testSetAndGetY() {
        vec3.setY(10);
        assertEquals(10, vec3.getY());
    }

    @Test
    public void testSetAndGetZ() {
        vec3.setZ(15);
        assertEquals(15, vec3.getZ());
    }

    @ParameterizedTest
    @MethodSource("provideVec3Values")
    public void testVec3Parameterized(Integer x, Integer y, Integer z) {
        Vec3<Integer> v = new Vec3<>(x, y, z);
        assertEquals(x, v.getX());
        assertEquals(y, v.getY());
        assertEquals(z, v.getZ());
    }

    static Stream<org.junit.jupiter.params.provider.Arguments> provideVec3Values() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of(1, 2, 3),
                org.junit.jupiter.params.provider.Arguments.of(-5, 100, 0),
                org.junit.jupiter.params.provider.Arguments.of(null, 0, 7),
                org.junit.jupiter.params.provider.Arguments.of(42, null, -1),
                org.junit.jupiter.params.provider.Arguments.of(104, -51, null));
    }
}
