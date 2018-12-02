package com.saayman.advent2018;

import com.saayman.advent2018.day1.FrequencyChange;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@RunWith(JUnit4.class)
public class Advent2018day1Test {

    @Test
    public void canCalculateNextFrequencyAccordingToExamples() {
        Assertions.assertThat(new FrequencyChange(0, 0).next()).isEqualTo(0);

        Assertions.assertThat(new FrequencyChange(0, 1).next()).isEqualTo(1);
        Assertions.assertThat(new FrequencyChange(1, -2).next()).isEqualTo(-1);
        Assertions.assertThat(new FrequencyChange(-1, 3).next()).isEqualTo(2);
        Assertions.assertThat(new FrequencyChange(2, 1).next()).isEqualTo(3);

        Assertions.assertThat(new FrequencyChange(0, Arrays.asList(1)).next()).isEqualTo(1);
        Assertions.assertThat(new FrequencyChange(1, Arrays.asList(-2)).next()).isEqualTo(-1);
        Assertions.assertThat(new FrequencyChange(-1, Arrays.asList(3)).next()).isEqualTo(2);
        Assertions.assertThat(new FrequencyChange(2, Arrays.asList(1)).next()).isEqualTo(3);

        List<Integer> exampleChanges = Arrays.asList(1, -2, 3, 1);
        Assertions.assertThat(new FrequencyChange(0, exampleChanges).next()).isEqualTo(3);

        List<Integer>[] otherExampleChanges = new List[]{Arrays.asList(1, 1, 1),
                                                        Arrays.asList(1, 1, -2),
                                                        Arrays.asList(-1, -2, -3)};

        Assertions.assertThat(new FrequencyChange(0, otherExampleChanges[0]).next()).isEqualTo(3);
        Assertions.assertThat(new FrequencyChange(0, otherExampleChanges[1]).next()).isEqualTo(0);
        Assertions.assertThat(new FrequencyChange(0, otherExampleChanges[2]).next()).isEqualTo(-6);
    }

}
