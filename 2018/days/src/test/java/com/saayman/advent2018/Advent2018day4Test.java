package com.saayman.advent2018;

import com.saayman.advent2018.day3.Claim;
import com.saayman.advent2018.day3.ClaimCoordinate;
import com.saayman.advent2018.day3.ClaimParser;
import com.saayman.advent2018.day3.OccupancyMapper;
import com.saayman.advent2018.day4.GuardEvent;
import com.saayman.advent2018.day4.GuardEventParser;
import com.saayman.advent2018.day4.GuardLog;
import com.saayman.advent2018.day4.GuardLogEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnit4.class)
public class Advent2018day4Test {

    @Test
    public void canParseFallsAsleepGuardEvent() {
        //[1518-08-12 00:43] falls asleep
        GuardEvent expected = new GuardEvent(1518, 8, 12, 0, 43, GuardEvent.Type.FALLS_ASLEEP, null);
        assertThat(GuardEventParser.parse("[1518-08-12 00:43] falls asleep")).isEqualTo(expected);
    }

    @Test
    public void canParseWakesUpGuardEvent() {
        //[1518-03-25 00:57] wakes up
        GuardEvent expected = new GuardEvent(1518, 3, 25, 0, 57, GuardEvent.Type.WAKES_UP, null);
        assertThat(GuardEventParser.parse("[1518-03-25 00:57] wakes up")).isEqualTo(expected);
    }

    @Test
    public void canParseBeginsShiftGuardEvent() {
        //[1518-11-01 00:00] Guard #10 begins shift
        GuardEvent expected = new GuardEvent(1518, 11, 1, 0, 0, GuardEvent.Type.BEGINS_SHIFT, 10);
        assertThat(GuardEventParser.parse("[1518-11-01 00:00] Guard #10 begins shift")).isEqualTo(expected);
        //[1518-11-01 00:00] Guard #1120 begins shift
        GuardEvent expected2= new GuardEvent(1518, 11, 1, 0, 0, GuardEvent.Type.BEGINS_SHIFT, 1120);
        assertThat(GuardEventParser.parse("[1518-11-01 00:00] Guard #1120 begins shift")).isEqualTo(expected2);
    }

    @Test
    public void canBuildSortedGuardEvent() {
        GuardEvent past = new GuardEvent(1518, 9, 1, 0, 0, GuardEvent.Type.BEGINS_SHIFT, 10);
        GuardEvent pst = new GuardEvent(1518, 10, 1, 0, 0, GuardEvent.Type.BEGINS_SHIFT, 10);
        GuardEvent pt = new GuardEvent(1518, 11, 1, 0, 0, GuardEvent.Type.BEGINS_SHIFT, 10);

        final List<GuardEvent> expected = Arrays.asList(past, pst, pt);
        assertThat(GuardEventParser.sortGuardEvents(Arrays.asList(pt, past, pst))).isEqualTo(expected);
    }

    @Test
    public void canAssignGuardToEvents() {
        GuardEvent begin = new GuardEvent(1518, 9, 1, 0, 0, GuardEvent.Type.BEGINS_SHIFT, 10);
        GuardEvent asleep = new GuardEvent(1518, 9, 1, 0, 10, GuardEvent.Type.FALLS_ASLEEP, null);
        GuardEvent awake = new GuardEvent(1518, 9, 1, 0, 13, GuardEvent.Type.WAKES_UP, null);
        GuardEvent asleep2 = new GuardEvent(1518, 9, 1, 0, 20, GuardEvent.Type.FALLS_ASLEEP, null);
        GuardEvent awake2 = new GuardEvent(1518, 9, 1, 0, 23, GuardEvent.Type.WAKES_UP, null);
        GuardEvent begin2 = new GuardEvent(1518, 9, 2, 0, 0, GuardEvent.Type.BEGINS_SHIFT, 11);
        GuardEvent asleep3 = new GuardEvent(1518, 9, 2, 0, 20, GuardEvent.Type.FALLS_ASLEEP, null);
        GuardEvent awake3 = new GuardEvent(1518, 9, 2, 0, 23, GuardEvent.Type.WAKES_UP, null);

        final List<GuardEvent> events = Arrays.asList(begin, asleep, awake, asleep2, awake2, begin2, asleep3,awake3);
        final List<GuardEvent> sortedEvents = GuardEventParser.sortGuardEvents(events);

        GuardEventParser.assignIds(sortedEvents);

        assertThat(awake.getGuardId()).isEqualTo(10);
        assertThat(awake3.getGuardId()).isEqualTo(11);

        List<GuardLogEvent> logEvents = GuardEventParser.toGuardLogEvents(sortedEvents);
        GuardLogEvent test = new GuardLogEvent(10, 9, GuardLogEvent.Type.WOKEN);

        assertThat(logEvents.contains(test)).isTrue();
    }

    @Test
    public void canGetTotalMinutesSleptAndMinuteMostSleptDuring() {
        GuardLog guardLog = new GuardLog();
        guardLog.loadEvent(new GuardLogEvent(1021, 0, GuardLogEvent.Type.WOKEN));
        guardLog.loadEvent(new GuardLogEvent(1022, 1, GuardLogEvent.Type.WOKEN));
        guardLog.loadEvent(new GuardLogEvent(1022, 12, GuardLogEvent.Type.SLEEPING));
        guardLog.loadEvent(new GuardLogEvent(1022, 12, GuardLogEvent.Type.SLEEPING));
        guardLog.loadEvent(new GuardLogEvent(1022, 0, GuardLogEvent.Type.SLEEPING));
        guardLog.loadEvent(new GuardLogEvent(1021, 0, GuardLogEvent.Type.SLEEPING));

        assertThat(guardLog.getIdSleptTheMost()).isEqualTo(1022);
        assertThat(guardLog.minuteMostSlept(1022)).isEqualTo(12);
        assertThat(guardLog.frequencySleptAtMinute(1022, 12)).isEqualTo(2);
        assertThat(guardLog.idHighestFrequecySleptAtAnyMinute()).isEqualTo(1022);
    }
}
