package com.saayman.advent2018.day7;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Day7Part1And2Solver {

    public static void main(String... args) throws IOException, URISyntaxException {
        part1And2();
    }

    private static void part1And2() throws URISyntaxException, IOException {
        URI fileName = ClassLoader.getSystemResource("day7/input.txt").toURI();
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            List<String> inputs = new ArrayList<>();
            stream.forEach(i -> inputs.add(i));
            Instructions instructions = new Instructions();
            instructions.load(inputs);
            System.out.println("Part1: "+instructions.orderedSteps());
            Instructions instructions2 = new Instructions();
            instructions2.load(inputs);
            instructions2.setStepTimeBase(60);
            System.out.println("Part2: "+instructions2.simulateTotalTime(5));
        }
    }
}
