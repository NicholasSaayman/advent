package com.saayman.advent2018.day4;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Day4Part1And2Solver {

    public static void main(String... args) throws IOException, URISyntaxException {
        part1And2();
    }

    private static void part1And2() throws URISyntaxException, IOException {
        URI fileName = ClassLoader.getSystemResource("day4/input.txt").toURI();
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            List<String> guardEventStrings = new ArrayList<>();
            stream.forEach(i -> guardEventStrings.add(i));

            List<GuardEvent> guardEvents = new ArrayList<>();
            for(String guardEventString: guardEventStrings) {
                guardEvents.add(GuardEventParser.parse(guardEventString));
            }

            List<GuardEvent> sorted = GuardEventParser.sortGuardEvents(guardEvents);
            GuardEventParser.assignIds(sorted);

            List<GuardLogEvent> logEvents = GuardEventParser.toGuardLogEvents(sorted);

            GuardLog log = new GuardLog();
            for(GuardLogEvent logEvent: logEvents) {
                log.loadEvent(logEvent);
            }
            final Integer idSleptTheMost = log.getIdSleptTheMost();
            System.out.println("part1: "+idSleptTheMost * log.minuteMostSlept(idSleptTheMost));

            final Integer idHighestMinute = log.idHighestFrequecySleptAtAnyMinute();
            System.out.println("part2: "+idHighestMinute * log.minuteMostSlept(idHighestMinute));
        }
    }
}
