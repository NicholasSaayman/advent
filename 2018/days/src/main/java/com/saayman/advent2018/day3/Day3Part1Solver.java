package com.saayman.advent2018.day3;

import com.saayman.advent2018.day2.ChecksumCalculator;
import com.saayman.advent2018.day2.DiffCalculator;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Day3Part1Solver {
    public static void main(String... args) throws IOException, URISyntaxException {
        System.out.println(part1());
    }

    private static int part1() throws URISyntaxException, IOException {
        URI fileName = ClassLoader.getSystemResource("day3/input.txt").toURI();
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            List<String> claimStrings = new ArrayList<>();
            stream.forEach(i -> claimStrings.add(i));

            List<Claim> claims = new ArrayList<>();
            for(String claimString: claimStrings) {
                claims.add(ClaimParser.parse(claimString));
            }

            return OccupancyMapper.inchesOverlapping(OccupancyMapper.mapOccupancy(claims));
        }
    }
}
