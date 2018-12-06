package com.saayman.advent2018;

import com.saayman.advent2018.day6.DistanceUtil;
import com.saayman.advent2018.day6.Grid;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnit4.class)
public class Advent2018day6Test {

    @Test
    public void canCalculateManhattanDistance() {
        double[] a = {1,1};
        double[] b = {1,6};
        assertThat(DistanceUtil.manhattanDistance(a,b)).isEqualTo(5);
    }

    @Test
    public void canDoTheExample() {
        double[] a = {1,1};
        double[] b = {1,6};
        double[] c = {8,3};
        double[] d = {3,4};
        double[] e = {5,5};
        double[] f = {8,9};
        double[][] coords = {a,b,c,d,e,f};
        Grid grid = new Grid();
        grid.loadCoords(coords);
        assertThat(grid.getMinX()).isEqualTo(1);
        assertThat(grid.getMaxX()).isEqualTo(8);
        assertThat(grid.getMinY()).isEqualTo(1);
        assertThat(grid.getMaxY()).isEqualTo(9);
        assertThat(grid.getCoordinates().size()).isEqualTo(6);
        assertThat(72).isEqualTo(grid.getLocations().size());
        assertThat(grid.getLargestArea()).isEqualTo(17);
    }

    @Test
    public void canDoTheExampleWithInputString() {
        List<String> coords = new ArrayList<>();
        coords.add("1,1");
        coords.add("1,6");
        coords.add("8,3");
        coords.add("3,4");
        coords.add("5,5");
        coords.add("8,9");
        Grid grid = new Grid();
        grid.loadCoords(coords);
        assertThat(grid.getLargestArea()).isEqualTo(17);
    }

    @Test
    public void canCalcDistanceToAllCordsAndRegionSize() {
        List<String> coords = new ArrayList<>();
        coords.add("1,1");
        coords.add("1,6");
        coords.add("8,3");
        coords.add("3,4");
        coords.add("5,5");
        coords.add("8,9");
        Grid grid = new Grid();
        grid.loadCoords(coords);
        assertThat(grid.getLocationMap().get("4,3").getDistanceToAll()).isEqualTo(30);
        assertThat(grid.sizeRegionLessThan(32)).isEqualTo(16);
    }
}