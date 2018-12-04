package com.saayman.advent2018.day4;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class GuardEventParser {

    public static GuardEvent parse(String eventString) {
        //000000000011111111112
        //012345678901234567890
        //[1518-08-12 00:43] falls asleep
        //[1518-11-01 00:00] Guard #10 begins shift
        //[1518-03-25 00:57] wakes up

        //Fugly
        return new GuardEvent(
                Integer.parseInt(eventString.substring(1,5)),
                Integer.parseInt(eventString.substring(6,8)),
                Integer.parseInt(eventString.substring(9,11)),
                Integer.parseInt(eventString.substring(12,14)),
                Integer.parseInt(eventString.substring(15,17)),
                eventString.contains("shift") ? GuardEvent.Type.BEGINS_SHIFT :
                        GuardEvent.Type.valueOf(eventString.substring(19).replace(" ","_").toUpperCase()),
                eventString.contains("shift") ? Integer.parseInt(eventString.substring(26,eventString.indexOf(" ",25))) :
                        null
                );
    }


    public static List<GuardEvent> sortGuardEvents(List<GuardEvent> unsorted) {
        List<GuardEvent> sorted = new ArrayList<>(unsorted);
        sorted.sort(Comparator.comparing(o -> o.getDateTime()));
        return sorted;
    }

    public static void assignIds(List<GuardEvent> sortedEvents) {
        Integer currentGuard = -1;
        for(int i = 0; i<sortedEvents.size();i++) {
            GuardEvent event = sortedEvents.get(i);
            if(event.getType().equals(GuardEvent.Type.BEGINS_SHIFT)) {
                currentGuard = event.getGuardId();
            } else {
                event.setGuardId(currentGuard);
            }
        }
    }

    public static List<GuardLogEvent> toGuardLogEvents(List<GuardEvent> sortedEvents) {
        final LocalDateTime earliest = sortedEvents.get(0).getDateTime();
        final LocalDateTime latest = sortedEvents.get(sortedEvents.size() - 1).getDateTime();

        GuardLogEvent.Type state = null;
        Integer currentGuard = -1;
        final long minutes = Duration.between(earliest, latest).toMinutes();

        Map<LocalDateTime, GuardEvent> eventAtTime = new HashMap<>();
        for(GuardEvent event: sortedEvents) {
            eventAtTime.put(event.getDateTime(), event);
        }

        List<GuardLogEvent> logEvents = new ArrayList<>();
        for(int minute = 0; minute <= minutes; minute++) {
            LocalDateTime now = earliest.plusMinutes(minute);
            if(eventAtTime.containsKey(now)) {
                GuardEvent event = eventAtTime.get(now);
                if(event.getType().equals(GuardEvent.Type.FALLS_ASLEEP)) {
                    state = GuardLogEvent.Type.SLEEPING;
                } else {
                    state = GuardLogEvent.Type.WOKEN;
                }
                currentGuard = event.getGuardId();
            }
            if(now.getHour()==0) {
                logEvents.add(new GuardLogEvent(currentGuard, now.getMinute(), state));
            }
        }

        return logEvents;
    }
}
