package com.saayman.advent2018;

import com.saayman.advent2018.day3.Claim;
import com.saayman.advent2018.day3.ClaimCoordinate;
import com.saayman.advent2018.day3.ClaimParser;
import com.saayman.advent2018.day3.OccupancyMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnit4.class)
public class Advent2018day3Test {

    @Test
    public void canCalculateClaimCoordinates() {
        Claim testA = new Claim("1",0,0,1,1);
        Set<ClaimCoordinate> expected = new HashSet<>();
        expected.add(new ClaimCoordinate(1,1));
        assertThat(testA.getOccupiedCoordinates()).isEqualTo(expected);

        Claim testB = new Claim("B",1,3,3,3);
        Set<ClaimCoordinate> expectedB = new HashSet<>();
        expectedB.add(new ClaimCoordinate(4,2));
        expectedB.add(new ClaimCoordinate(4,3));
        expectedB.add(new ClaimCoordinate(4,4));
        expectedB.add(new ClaimCoordinate(5,2));
        expectedB.add(new ClaimCoordinate(5,3));
        expectedB.add(new ClaimCoordinate(5,4));
        expectedB.add(new ClaimCoordinate(6,2));
        expectedB.add(new ClaimCoordinate(6,3));
        expectedB.add(new ClaimCoordinate(6,4));

        assertThat(testB.getOccupiedCoordinates()).isEqualTo(expectedB);
    }

    @Test
    public void canCalculateOverlap() {
        Claim testA = new Claim("1",0,0,1,1);
        Claim testB = new Claim("1",1,1,1,1);
        Claim testC = new Claim("1",0,0,1,1);
        Claim testD = new Claim("1",1,1,2,2);

        Set<ClaimCoordinate> expected = new HashSet<>();
        expected.add(new ClaimCoordinate(1,1));
        assertThat(testA.overLap(testC).containsAll(expected)).isTrue();
        assertThat(testA.overLap(testB).isEmpty()).isTrue();
        assertThat(testB.overLap(testD).containsAll(expected)).isFalse();

        Set<ClaimCoordinate> b_d_overlap = new HashSet<>();
        b_d_overlap.add(new ClaimCoordinate(2,2));
        assertThat(testB.overLap(testD).containsAll(b_d_overlap)).isTrue();
    }

    @Test
    public void canParseClaim() {
        String sample = "#1 @ 1,3: 4x4";
        assertThat(ClaimParser.parse(sample)).isEqualTo(new Claim("1",1,3,4,4));
    }

    @Test
    public void canMapCoordinateOccupancyAndDetermineOverlappingInches() {
        Map<ClaimCoordinate, Integer> occupancyExpected = new HashMap<>();
        occupancyExpected.put(new ClaimCoordinate(1,1), 2);
        occupancyExpected.put(new ClaimCoordinate(2,2), 2);
        occupancyExpected.put(new ClaimCoordinate(3,3), 1);

        Claim testA = new Claim("1",0,0,1,1);
        Claim testB = new Claim("2",1,1,1,1);
        Claim testC = new Claim("3",0,0,1,1);
        Claim testD = new Claim("4",1,1,1,1);
        Claim testE = new Claim("5",2,2,1,1);
        List<Claim> claims = Arrays.asList(testA, testB, testC, testD, testE);

        Map<ClaimCoordinate, Integer> occupancy = OccupancyMapper.mapOccupancy(claims);
        assertThat(occupancy).isEqualTo(occupancyExpected);

        assertThat(OccupancyMapper.inchesOverlapping(occupancy)).isEqualTo(2);

        assertThat(OccupancyMapper.hasOccupancyOverlap(testE,occupancy)).isFalse();
    }

}
