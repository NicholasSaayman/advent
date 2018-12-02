package com.saayman.advent2018;

import com.saayman.advent2018.day1.FrequencyRepeatDetector;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnit4.class)
public class Advent2018day2Test {

    @Test
    public void canCalculateRepeatedFrequencyAccordingToExamples() {
        Assertions.assertThat(new FrequencyRepeatDetector(0, Arrays.asList(1, -1)).firstRepeated()).isEqualTo(0);
        Assertions.assertThat(new FrequencyRepeatDetector(0, Arrays.asList(3,3,4,-2,-4)).firstRepeated()).isEqualTo(10);
        Assertions.assertThat(new FrequencyRepeatDetector(0, Arrays.asList(-6,3,8,5,-6)).firstRepeated()).isEqualTo(5);
        Assertions.assertThat(new FrequencyRepeatDetector(0, Arrays.asList(7,7,-2,-7,-4)).firstRepeated()).isEqualTo(14);
    }

}
