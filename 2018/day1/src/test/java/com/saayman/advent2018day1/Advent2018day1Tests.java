package com.saayman.advent2018day1;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class Advent2018day1Tests {

    @Test
    public void canCalculateNextFrequencyAccordingToExamples() {
        assertThat(new FrequencyChange(0, 0).next()).isEqualTo(0);

        assertThat(new FrequencyChange(0, 1).next()).isEqualTo(1);
        assertThat(new FrequencyChange(1, -2).next()).isEqualTo(-1);
        assertThat(new FrequencyChange(-1, 3).next()).isEqualTo(2);
        assertThat(new FrequencyChange(2, 1).next()).isEqualTo(3);

        assertThat(new FrequencyChange(0, Arrays.asList(1)).next()).isEqualTo(1);
        assertThat(new FrequencyChange(1, Arrays.asList(-2)).next()).isEqualTo(-1);
        assertThat(new FrequencyChange(-1, Arrays.asList(3)).next()).isEqualTo(2);
        assertThat(new FrequencyChange(2, Arrays.asList(1)).next()).isEqualTo(3);

        List<Integer> exampleChanges = Arrays.asList(1, -2, 3, 1);
        assertThat(new FrequencyChange(0, exampleChanges).next()).isEqualTo(3);

        List<Integer>[] otherExampleChanges = new List[]{Arrays.asList(1, 1, 1),
                                                        Arrays.asList(1, 1, -2),
                                                        Arrays.asList(-1, -2, -3)};

        assertThat(new FrequencyChange(0, otherExampleChanges[0]).next()).isEqualTo(3);
        assertThat(new FrequencyChange(0, otherExampleChanges[1]).next()).isEqualTo(0);
        assertThat(new FrequencyChange(0, otherExampleChanges[2]).next()).isEqualTo(-6);
    }

}
