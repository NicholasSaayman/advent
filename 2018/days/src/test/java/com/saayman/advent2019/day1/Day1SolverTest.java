package com.saayman.advent2019.day1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(JUnit4.class)
public class Day1SolverTest {

    @Test
    public void testModule() {
        Module test = new Module("test", 12);
        assertEquals(2, test.requiredFuelForMass());
        test.setMass(14);
        assertEquals(2, test.requiredFuelForMass());
        assertEquals(2, test.requiredFuel());
        test.setMass(1969);
        assertEquals(654, test.requiredFuelForMass());
        assertEquals(966, test.requiredFuel());
        test.setMass(100756);
        assertEquals(33583, test.requiredFuelForMass());
        assertEquals(50346, test.requiredFuel() );
    }
}