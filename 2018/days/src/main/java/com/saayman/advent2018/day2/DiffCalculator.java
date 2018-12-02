package com.saayman.advent2018.day2;

import java.util.ArrayList;
import java.util.List;

public class DiffCalculator {
    private final List<String> boxIds;

    public List<String> getPrototypes() {
        return prototypes;
    }

    private List<String> prototypes = new ArrayList<>();
    public DiffCalculator(List<String> boxIds) {
        this.boxIds = boxIds;
    }

    public DiffCalculator findPrototypeBoxes() {
        for(String boxToCheck: boxIds) {
            for(String otherBox: boxIds) {
                if(new Checker(boxToCheck).differsBy1(otherBox.toCharArray())) {
                    if(!prototypes.contains(boxToCheck)) prototypes.add(boxToCheck);
                    if(!prototypes.contains(otherBox)) prototypes.add(otherBox);
                }
            }
        }
        return this;
    }

    public String protoDiff() {
        final String first = prototypes.get(0);
        final String second = prototypes.get(1);
        String diff = "";
        for(int i=0; i< first.length();i++) {
            if(first.charAt(i)==second.charAt(i)) {
                diff = diff + first.charAt(i);
            }
        }
        return diff;
    }
}
