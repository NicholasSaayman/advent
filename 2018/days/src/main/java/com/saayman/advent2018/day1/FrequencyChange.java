package com.saayman.advent2018.day1;

import java.util.List;

public class FrequencyChange {
    private final List<Integer> changes;
    private final Integer initial;
    private final Integer change;
    private final int next;

    public FrequencyChange(int initial, int change) {
        this.initial = initial;
        this.change = change;
        this.changes = null;
        this.next = nextSingle();
    }

    public FrequencyChange(int initial, List<Integer> changes) {
        if(changes==null || changes.isEmpty()) throw new RuntimeException("Come on init this stuff.");
        this.initial = initial;
        this.changes = changes;
        this.change = null;
        this.next = nextList();
    }

    public int next() {
        return this.next;
    }

    private int nextList() {
        FrequencyChange next = new FrequencyChange(this.initial, changes.get(0));
        for(int i = 1; i < changes.size(); i++) {
            next = new FrequencyChange(next.next(), changes.get(i));
        }
        return next.next();
    }

    private int nextSingle() {
        return initial + change;
    }

    private boolean isList() {
        if(this.change==null && this.changes==null) throw new RuntimeException("Don't do both at the same time!!!");
        if(this.change==null) return true;
        return false;
    }
}
