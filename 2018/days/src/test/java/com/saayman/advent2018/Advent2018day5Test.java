package com.saayman.advent2018;

import com.saayman.advent2018.day5.Polymer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnit4.class)
public class Advent2018day5Test {

    @Test
    public void canProcessPolymerReactions() {
        // Simple
        Polymer simple = new Polymer("aA"); //Unstable
        simple.stablize();
        assertThat(simple.getStable()).isEqualTo("");
        assertThat(simple.getReactions()).isEqualTo(1);

        // Harder
        Polymer harder = new Polymer("dabAcCaCBAcCcaDA"); //Unstable
        harder.stablize();
        assertThat(harder.getStable()).isEqualTo("dabCBAcaDA");
        assertThat(harder.getReactions()).isEqualTo(3);
    }

    @Test
    public void canProcessPolymerPreventorReactions() {
        // Harder
        Polymer harder = new Polymer("dabAcCaCBAcCcaDA"); //Unstable
        harder.determinePreventor();
        assertThat(harder.getPreventorUnit()).isEqualTo('c');
        assertThat(harder.getShortestPolymer()).isEqualTo(4);
    }
}