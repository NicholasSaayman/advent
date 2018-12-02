package com.saayman.advent2018.day2;

import java.util.List;

public class ChecksumCalculator {
    private final List<String> ids;
    private final int totalTwice;
    private final int totalThreeTimes;

    public ChecksumCalculator(List<String> ids) {
        this.ids = ids;
        totalTwice = calcTotalTwice();
        totalThreeTimes = calcTotalThreeTimes();
    }

    private int calcTotalThreeTimes() {
        int count = 0;
        for(String id: ids) {
            if(new Checker(id).hasExactlyThreeTimes()) {
                count++;
            }
        }
        return count;
    }

    private int calcTotalTwice() {
        int count = 0;
        for(String id: ids) {
            if(new Checker(id).hasExactlyTwice()) {
                count++;
            }
        }
        return count;
    }

    public int totalTwice() {
        return totalTwice;
    }

    public int getTotalThreeTimes() {
        return totalThreeTimes;
    }

    public int getChecksum() {
        return totalTwice * totalThreeTimes;
    }
}
