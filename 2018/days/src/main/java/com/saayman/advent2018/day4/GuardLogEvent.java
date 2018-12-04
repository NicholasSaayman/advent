package com.saayman.advent2018.day4;

import java.util.Objects;

public class GuardLogEvent {
    public Integer getId() {
        return id;
    }

    public Integer getMinute() {
        return minute;
    }

    public Type getType() {
        return type;
    }

    private final Integer id;
    private final Integer minute;
    private final Type type;

    public GuardLogEvent(Integer id, Integer minute, GuardLogEvent.Type type) {
        this.id = id;
        this.minute = minute;
        this.type = type;
    }

    public enum Type {WOKEN,SLEEPING}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GuardLogEvent that = (GuardLogEvent) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getMinute(), that.getMinute()) &&
                getType() == that.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getMinute(), getType());
    }

    @Override
    public String toString() {
        return "GuardLogEvent{" +
                "id=" + id +
                ", minute=" + minute +
                ", type=" + type +
                '}';
    }
}
