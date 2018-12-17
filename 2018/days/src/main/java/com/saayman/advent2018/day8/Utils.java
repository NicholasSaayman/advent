package com.saayman.advent2018.day8;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Utils {
    static List<Integer> parse(String input) {
        List<Integer> numberList;
        String parsing = input;
        StringTokenizer numberTokens = new StringTokenizer(parsing, " ", false);
        numberList = new ArrayList<>();
        while (numberTokens.hasMoreTokens()){
            numberList.add(Integer.parseInt(numberTokens.nextToken()));
        }
        return numberList;
    }
}
