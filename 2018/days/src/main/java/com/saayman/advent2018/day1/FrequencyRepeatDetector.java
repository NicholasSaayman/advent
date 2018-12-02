package com.saayman.advent2018.day1;

import java.util.ArrayList;
import java.util.List;

public class FrequencyRepeatDetector {
    private final Integer firstRepeated;

    public FrequencyRepeatDetector(Integer initial, List<Integer> changes) {
        this.firstRepeated = detectFirstRepeated(initial, changes);
    }

    private Integer detectFirstRepeated(Integer initial, List<Integer> changes) {
        List<Integer> detectedFrequencies = new ArrayList<>();
        detectedFrequencies.add(initial);
        int runningInitial = initial;
        boolean found = false;
        Integer value = null;
        while(!found) {
            for(int i = 0; i < changes.size(); i++) {
                final int next = new FrequencyChange(runningInitial, changes.get(i)).next();
                if(detectedFrequencies.contains(next)) {
                    value = next;
                    found = true;
                    break;
                } else {
                    detectedFrequencies.add(next);
                    runningInitial = next;
                }
            }
        }
        return value;
    }

    public Integer firstRepeated() {
        return this.firstRepeated;
    }
}
