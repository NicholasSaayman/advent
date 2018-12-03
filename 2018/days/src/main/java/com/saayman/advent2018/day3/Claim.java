package com.saayman.advent2018.day3;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Claim {
    private String id;
    private Integer fromLeft;
    private Integer fromTop;
    private Integer width;
    private Integer height;

    public Claim(String id, Integer fromLeft, Integer fromTop, Integer width, Integer height) {
        this.id = id;
        this.fromLeft = fromLeft;
        this.fromTop = fromTop;
        this.width = width;
        this.height = height;
    }


    public Set<ClaimCoordinate> getOccupiedCoordinates() {
        Set<ClaimCoordinate> occupied = new HashSet<>();
        for(int occupiedRow = (fromTop+1); occupiedRow <= fromTop+height; occupiedRow++) {
            for(int occupiedColumn = (fromLeft+1); occupiedColumn <= fromLeft+width; occupiedColumn++) {
                occupied.add(new ClaimCoordinate(occupiedRow,occupiedColumn));
            }
        }
        return occupied;
    }

    public Collection<ClaimCoordinate> overLap(Claim other) {
        return CollectionUtils.intersection(this.getOccupiedCoordinates(),other.getOccupiedCoordinates());
    }

    @Override
    public String toString() {
        return "Claim{" +
                "id='" + id + '\'' +
                ", fromLeft=" + fromLeft +
                ", fromTop=" + fromTop +
                ", width=" + width +
                ", height=" + height +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Claim claim = (Claim) o;
        return Objects.equals(id, claim.id) &&
                Objects.equals(fromLeft, claim.fromLeft) &&
                Objects.equals(fromTop, claim.fromTop) &&
                Objects.equals(width, claim.width) &&
                Objects.equals(height, claim.height);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fromLeft, fromTop, width, height);
    }
}
