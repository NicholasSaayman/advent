package com.saayman.advent2018.day4;

import java.time.LocalDateTime;
import java.util.Objects;

public class GuardEvent {
    private LocalDateTime dateTime;
    private final int year;
    private final int month;
    private final int day;
    private final int hour;
    private final int minute;

    public Type getType() {
        return type;
    }

    private final Type type;

    public Integer getGuardId() {
        return guardId;
    }

    public void setGuardId(Integer guardId) {
        this.guardId = guardId;
    }

    private Integer guardId;

    public GuardEvent(int year, int month, int day, int hour, int minute, Type type, Integer guardId) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.type = type;
        this.guardId = guardId;
        this.dateTime = LocalDateTime.of(year,month,day,hour,minute);
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public enum Type {FALLS_ASLEEP,WAKES_UP,BEGINS_SHIFT}

    @Override
    public String toString() {
        return "GuardEvent{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", hour=" + hour +
                ", minute=" + minute +
                ", type=" + type +
                ", guardId=" + guardId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GuardEvent that = (GuardEvent) o;
        return year == that.year &&
                month == that.month &&
                day == that.day &&
                hour == that.hour &&
                minute == that.minute &&
                type == that.type &&
                Objects.equals(guardId, that.guardId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, month, day, hour, minute, type, guardId);
    }
}
