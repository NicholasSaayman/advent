package com.saayman.advent2018.day4;

import java.util.*;


public class GuardLog {
    public Integer getIdSleptTheMost() {
        Integer max = 0;
        Integer maxId = 0;
        for(Guard guard: log.values()) {
            Integer sleepMinutes = guard.getSleepMinutes();
            if(sleepMinutes > max) {
                max = sleepMinutes;
                maxId = guard.id;
            }
        }
        return maxId;
    }

    public Integer minuteMostSlept(Integer id) {
        return log.get(id).minuteMostSlept();
    }

    public Integer frequencySleptAtMinute(Integer id, Integer minute) {
        return log.get(id).frequecySleptAtMinute(minute);
    }

    public Integer idHighestFrequecySleptAtAnyMinute() {
        Integer maxTimes = 0;
        Integer maxId = 0;
        for(Guard guard: log.values()) {
            Integer minute = guard.minuteMostSlept();
            if(guard.asleep[minute] > maxTimes) {
                maxTimes = guard.asleep[minute];
                maxId = guard.id;
            }
        }
        return maxId;
    }

    public class Guard {
        public Integer id;
        public int[] awake = new int[60];
        public int[] asleep = new int[60];
        public Guard(Integer id) {
            this.id = id;
        }
        public Integer getSleepMinutes() {
            return Arrays.stream(asleep).reduce(0, Integer::sum);
        }
        public Integer minuteMostSlept() {
            int max = -1;
            int minute = -1;
            for(int min = 0; min < 60; min++) {
                if(asleep[min] >= max) {
                    max = asleep[min];
                    minute = min;
                }
            }
            return minute;
        }
        public Integer frequecySleptAtMinute(Integer minute) {
            return asleep[minute];
        }
    }

    public Map<Integer, Guard> log = new HashMap<>();

    public void loadEvent(GuardLogEvent guardLogEvent) {
        if(!log.containsKey(guardLogEvent.getId())) {
            log.put(guardLogEvent.getId(), new Guard(guardLogEvent.getId()));
        }

        Guard toUpdate = log.get(guardLogEvent.getId());

        if(guardLogEvent.getType().equals(GuardLogEvent.Type.SLEEPING)) {
            toUpdate.asleep[guardLogEvent.getMinute()]++;
        } else {
            toUpdate.awake[guardLogEvent.getMinute()]++;
        }
    }

}
