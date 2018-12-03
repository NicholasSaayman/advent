package com.saayman.advent2018.day3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OccupancyMapper {
    public static Map<ClaimCoordinate, Integer> mapOccupancy(List<Claim> claims) {
        Map<ClaimCoordinate, Integer> occupancyMap = new HashMap<ClaimCoordinate, Integer>();

        for (Claim claim : claims) {
            Set<ClaimCoordinate> occupiedCoordinates = claim.getOccupiedCoordinates();
            for(ClaimCoordinate coord: occupiedCoordinates) {
                Integer count = occupancyMap.get(coord);
                occupancyMap.put(coord, (count == null) ? 1 : count + 1);
            }
        }
        return occupancyMap;
    }

    public static Integer inchesOverlapping(Map<ClaimCoordinate, Integer> occupancyMap) {
        int count = 0;
        for(Map.Entry<ClaimCoordinate, Integer> entry: occupancyMap.entrySet()) {
            if(entry.getValue() > 1) {
                count++;
            }
        }
        return count;
    }

    public static Boolean hasOccupancyOverlap(Claim claim, Map<ClaimCoordinate, Integer> occupancyMap) {
        boolean occupancyOverlaps = false;
        for(ClaimCoordinate occupied: claim.getOccupiedCoordinates()) {
            if(occupancyMap.get(occupied) > 1) {
                occupancyOverlaps = true;
                break;
            }
        }
        return occupancyOverlaps;
    }
}
