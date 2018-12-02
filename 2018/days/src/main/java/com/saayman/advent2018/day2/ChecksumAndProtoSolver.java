package com.saayman.advent2018.day2;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ChecksumAndProtoSolver {
    public static void main(String... args) throws IOException, URISyntaxException {
        System.out.println(part1());
        System.out.println(part2());
    }

    private static String part2() throws URISyntaxException, IOException {
        URI fileName = ClassLoader.getSystemResource("day2/input.txt").toURI();
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            List<String> ids = new ArrayList<>();
            stream.forEach(i -> ids.add(i));
            DiffCalculator calculator = new DiffCalculator(ids);
            calculator.findPrototypeBoxes().getPrototypes();
            return calculator.protoDiff();
        }
    }

    private static int part1() throws URISyntaxException, IOException {
        URI fileName = ClassLoader.getSystemResource("day2/input.txt").toURI();
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            List<String> ids = new ArrayList<>();
            stream.forEach(i -> ids.add(i));
            ChecksumCalculator calculator = new ChecksumCalculator(ids);
            return calculator.getChecksum();
        }
    }
}
