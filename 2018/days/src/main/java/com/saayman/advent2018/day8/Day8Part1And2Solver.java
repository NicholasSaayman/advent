package com.saayman.advent2018.day8;


import com.saayman.advent2018.day7.Instructions;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Day8Part1And2Solver {

    public static void main(String... args) throws IOException, URISyntaxException {
        part1And2();
    }

    //-Xss is needed
    private static void part1And2() throws URISyntaxException, IOException {
        URI fileName = ClassLoader.getSystemResource("day8/input.txt").toURI();
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            List<String> inputs = new ArrayList<>();
            stream.forEach(i -> inputs.add(i));
            System.out.println("Part1: "+new NodeTree(inputs.get(0)).sumMetaEntries());
            System.out.println("Part2: "+new NodeTree(inputs.get(0)).root.value());
        }
    }
}
