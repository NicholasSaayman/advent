package com.saayman.advent2019.day1;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Day1Solver {

    public static void main(String... args) throws IOException, URISyntaxException {
        part1And2();
    }

    //-Xss is needed
    private static void part1And2() throws URISyntaxException, IOException {
        URI fileName = ClassLoader.getSystemResource("2019/day1/input.txt").toURI();
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            List<String> inputs = new ArrayList<>();
            stream.forEach(i -> inputs.add(i));

            int sumFuelForMass = inputs.stream().map(i -> new Module("Mod " + Math.random() * 7000, Integer.parseInt(i))).mapToInt(module -> module.requiredFuelForMass()).sum();
            System.out.println("Part1: "+sumFuelForMass);

            int sumFuel = inputs.stream().map(i -> new Module("Mod " + Math.random() * 7000, Integer.parseInt(i))).mapToInt(module -> module.requiredFuel()).sum();
            System.out.println("Part2: "+sumFuel);

        }
    }
}
