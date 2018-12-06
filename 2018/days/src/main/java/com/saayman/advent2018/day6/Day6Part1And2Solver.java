package com.saayman.advent2018.day6;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Day6Part1And2Solver {

    public static void main(String... args) throws IOException, URISyntaxException {
        part1And2();
    }

    private static void part1And2() throws URISyntaxException, IOException {
        URI fileName = ClassLoader.getSystemResource("day6/input.txt").toURI();
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            List<String> coords = new ArrayList<>();
            stream.forEach(i -> coords.add(i));
            Grid grid = new Grid();
            grid.loadCoords(coords);
            System.out.println("Part1: "+grid.getLargestArea());
            System.out.println("Part2: "+grid.sizeRegionLessThan(10000));
        }
    }
}
