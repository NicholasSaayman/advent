package com.saayman.advent2018day1;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FrequencySolver {
    public static void main(String... args) throws URISyntaxException {
        URI fileName = ClassLoader.getSystemResource("input.txt").toURI();
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            List<Integer> changes = new ArrayList<>();
            stream.forEachOrdered(i -> changes.add(Integer.parseInt(i)));
            FrequencyChange solved = new FrequencyChange(0, changes);
            System.out.println(solved.next());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
