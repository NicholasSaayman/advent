package com.saayman.advent2018;

import com.saayman.advent2018.day2.Checker;
import com.saayman.advent2018.day2.ChecksumCalculator;
import com.saayman.advent2018.day2.DiffCalculator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnit4.class)
public class Advent2018day2Test {

    @Test
    public void canCountAppearances() {
        final String test = "abcddefffgh";
        assertThat(new Checker(test).countAppearances('a')).isEqualTo(1);
        assertThat(new Checker(test).countAppearances('z')).isEqualTo(0);
        assertThat(new Checker(test).countAppearances('d')).isEqualTo(2);
        assertThat(new Checker(test).countAppearances('f')).isEqualTo(3);
    }

    @Test
    public void canCountExactlyTwice() {
        assertThat(new Checker("abcdefgh").hasExactlyTwice()).isFalse();
        assertThat(new Checker("aacdefgh").hasExactlyTwice()).isTrue();
        assertThat(new Checker("aaadefgh").hasExactlyTwice()).isFalse();
        assertThat(new Checker("aaaadefgh").hasExactlyTwice()).isFalse();
        assertThat(new Checker("aacddefgh").hasExactlyTwice()).isTrue();
        assertThat(new Checker("abcddefgh").hasExactlyTwice()).isTrue();
        assertThat(new Checker("abcdefghh").hasExactlyTwice()).isTrue();
    }

    @Test
    public void canCountExactlyThreeTimes() {
        assertThat(new Checker("abcdefgh").hasExactlyThreeTimes()).isFalse();
        assertThat(new Checker("aaacdefgh").hasExactlyThreeTimes()).isTrue();
        assertThat(new Checker("aadefgh").hasExactlyThreeTimes()).isFalse();
        assertThat(new Checker("aaaadefgh").hasExactlyThreeTimes()).isFalse();
        assertThat(new Checker("aaacdddefgh").hasExactlyThreeTimes()).isTrue();
        assertThat(new Checker("abcdddefgh").hasExactlyThreeTimes()).isTrue();
        assertThat(new Checker("abcdefghhh").hasExactlyThreeTimes()).isTrue();
    }

    @Test
    public void canChecksum() {
        List<String> sample = Arrays.asList(
                "abcdef",
                "bababc",
                "abbcde",
                "abcccd",
                "aabcdd",
                "abcdee",
                "ababab");
        assertThat(new ChecksumCalculator(sample).totalTwice()).isEqualTo(4);
        assertThat(new ChecksumCalculator(sample).getTotalThreeTimes()).isEqualTo(3);
        assertThat(new ChecksumCalculator(sample).getChecksum()).isEqualTo(12);
    }

    @Test
    public void canDifferby1check() {
        assertThat(new Checker("abcdef").differsBy1("abcdef".toCharArray())).isFalse();
        assertThat(new Checker("abcdrf").differsBy1("abcdef".toCharArray())).isTrue();
        assertThat(new Checker("abrdrf").differsBy1("abcdef".toCharArray())).isFalse();
    }

    @Test
    public void canFindMatchingBoxes() {
        final DiffCalculator calculator = new DiffCalculator(Arrays.asList(
                "abcde",
                "fghij",
                "klmno",
                "pqrst",
                "fguij",
                "axcye",
                "wvxyz"));
        List<String> boxes = calculator.findPrototypeBoxes().getPrototypes();
        assertThat(boxes.size()).isEqualTo(2);
        assertThat(boxes.contains("fghij")).isTrue();
        assertThat(boxes.contains("fguij")).isTrue();
        assertThat(calculator.protoDiff()).isEqualTo("fgij");
    }
}
