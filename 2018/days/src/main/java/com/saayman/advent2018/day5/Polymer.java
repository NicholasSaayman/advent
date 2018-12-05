package com.saayman.advent2018.day5;

import java.util.Stack;

public class Polymer {
    private String unstable;
    private String stable;
    private Boolean stablized;
    private Integer reactions;
    private char preventorUnit;
    private int shortestPolymer;

    public Polymer(String unstable) {
        this.unstable = unstable;
        stablized = false;
        reactions = 0;
    }

    public char getPreventorUnit() {
        return preventorUnit;
    }

    public int getShortestPolymer() {
        return shortestPolymer;
    }

    public String getUnstable() {
        return unstable;
    }

    public String getStable() {
        return stable;
    }

    public Boolean isStablized() {
        return stablized;
    }

    public Integer getReactions() {
        return reactions;
    }

    public int getStableUnits() {
        if (isStablized()) {
            return stable.length();
        }
        return -1;
    }

    public void stablize() {
        if (isStablized()) return;
        Stack<Character> polyStack = new Stack<>();

        char[] unstableChars = unstable.toCharArray();
        for (char unstableChar : unstableChars) {
            if (!polyStack.empty()) {
                char top = polyStack.peek();
                polyStack.push(unstableChar);
                char newTop = unstableChar;
                if (reacts(top, newTop)) {
                    polyStack.pop(); //topNew
                    polyStack.pop(); //top
                    this.reactions++;
                }
            } else {
                polyStack.push(unstableChar);
            }
        }
        this.stablized = true;
        Character[] chars = polyStack.toArray(new Character[0]);
        this.stable = "";
        for (Character c : chars)
            this.stable += c.toString();
    }

    private boolean reacts(char top, char newTop) {
        if ((Character.isUpperCase(top) && Character.isLowerCase(newTop)) ||
                (Character.isLowerCase(top) && Character.isUpperCase(newTop))) {
            return Character.toLowerCase(top) == Character.toLowerCase(newTop);
        }
        return false;
    }

    public void remove(String c) {
        this.unstable = unstable.replace(c.toUpperCase(), "");
        this.unstable = unstable.replace(c.toLowerCase(), "");
    }

    public void determinePreventor() {
        char biggestPreventor = 0;
        int smallestPolymer=999999999;
        for(char unitCandidate = 'a'; unitCandidate <='z'; unitCandidate ++ ) {
            Polymer experiment = new Polymer(unstable);
            experiment.remove(""+unitCandidate);
            experiment.stablize();
            if(experiment.getStableUnits() < smallestPolymer) {
                biggestPreventor = unitCandidate;
                smallestPolymer = experiment.getStableUnits();
            }
        }
        this.preventorUnit = biggestPreventor;
        this.shortestPolymer = smallestPolymer;
    }
}
