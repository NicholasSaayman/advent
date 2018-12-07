package com.saayman.advent2018;

import com.saayman.advent2018.day7.Instructions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnit4.class)
public class Advent2018day7Test {

    private List<String> input;

    @Before
    public void setUp() throws Exception {
        input = new ArrayList<>();
        input.add("Step C must be finished before step A can begin.");
        input.add("Step C must be finished before step F can begin.");
        input.add("Step A must be finished before step B can begin.");
        input.add("Step A must be finished before step D can begin.");
        input.add("Step B must be finished before step E can begin.");
        input.add("Step D must be finished before step E can begin.");
        input.add("Step F must be finished before step E can begin.");
    }

    @Test
    public void canParseInstructionsAndSortSteps() {
        Instructions instructions = new Instructions();
        instructions.load(input);
        assertThat(instructions.totalSteps()).isEqualTo(6);
        assertThat(instructions.totalStepsBefore("E")).isEqualTo(3);
        assertThat(instructions.totalStepsAfter("E")).isEqualTo(0);
        assertThat(instructions.nextReadySteps().size()).isEqualTo(1);
        assertThat(instructions.nextReadySteps().get(0)).isEqualTo("C");
        assertThat(instructions.orderedSteps()).isEqualTo("CABDFE");
    }

    @Test
    public void canSimulateWorkers() {
        Instructions instructions = new Instructions();
        instructions.load(input);
        assertThat(instructions.orderedSimulationSteps()).isEqualTo("CAFBDE");
        instructions.setStepTimeBase(1);
        assertThat(instructions.getStepTime("C")).isEqualTo(4);
        assertThat(instructions.simulateTotalTime(2)).isEqualTo(20);
    }
}