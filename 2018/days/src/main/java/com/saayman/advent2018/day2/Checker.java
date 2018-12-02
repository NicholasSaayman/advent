package com.saayman.advent2018.day2;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Checker {
    private final String toCheck;
    private final char[] toCheckChars;
    private final Map<Character, Integer> charCounts;

    public Checker(String toCheck) {
        this.toCheck = toCheck;
        this.toCheckChars = toCheck.toCharArray();
        this.charCounts = initCounts();
    }

    private Map<Character, Integer> initCounts() {
        Map<Character, Integer> calculated = new HashMap<>();
        for(int i = 0; i<toCheck.length(); i++) {
            Character charToCount = toCheck.charAt(i);
            calculated.put(charToCount, countAppearances(charToCount));
        }
        return calculated;
    }

    public boolean hasExactlyTwice() {
        return charCounts.containsValue(2);
    }

    public boolean hasExactlyThreeTimes() {
        return charCounts.containsValue(3);
    }

    public boolean differsBy1(char[] other) {
        int[] diffMap = new int[other.length];
        for(int i = 0; i<other.length;i++) {
            if(toCheckChars[i] != other[i]) {
                diffMap[i] = 1;
            } else {
                diffMap[i] = 0;
            }
        }
        return IntStream.of(diffMap).sum() == 1;
    }

    public int countAppearances(char charToCount) {
        int count = 0;
        for(char ch: toCheckChars) {
            if(ch == charToCount) {
                count++;
            }
        }
        return count;
    }
}
