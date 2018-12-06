package com.saayman.advent2018.day6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grid {
    private List<Location> locations = new ArrayList<>();
    private List<Coordinate> coordinates = new ArrayList<>();
    private int minX = 99999999;
    private int maxX = -99999999;
    private int minY = 99999999;
    private int maxY = -99999999;

    @Override
    public String toString() {
        return "Grid{" +
                "locations=" + locations +
                ", coordinates=" + coordinates +
                ", minX=" + minX +
                ", maxX=" + maxX +
                ", minY=" + minY +
                ", maxY=" + maxY +
                '}';
    }

    public List<Location> getLocations() {
        return locations;
    }

    public List<Coordinate> getCoordinates() {
        return coordinates;
    }

    public int getMinX() {
        return minX;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMinY() {
        return minY;
    }

    public int getMaxY() {
        return maxY;
    }

    public int getLargestArea() {
        int largest = -1;
        for(Coordinate cord: coordinates) {
            if(cord.area() > largest) {
                largest = cord.area();
            }
        }
        return largest;
    }

    public void loadCoords(double[][] coords) {
        if(coordinates.size() > 0) throw new RuntimeException("Please only load once.");

        setupCoordinates(coords);

        setupLocations();

        markLocations();

    }

    private void markLocations() {
        for(Location location: locations) {
            int shortest = 99999999;
            for(Coordinate coordinate: coordinates) {
                int distance = DistanceUtil.manhattanDistance(location.getPosition(), coordinate.getPosition());
                if(distance <= shortest) {
                    shortest = distance;
                }
            }
            for(Coordinate coordinate: coordinates) {
                int distance = DistanceUtil.manhattanDistance(location.getPosition(), coordinate.getPosition());
                if(distance == shortest) {
                    location.closest.add(coordinate);
                }
            }
            if(location.closest.size() == 1) {
                location.closest.get(0).closest.add(location);
            }
        }

    }

    private void setupLocations() {
        for(int x = minX; x <= maxX; x++) {
            for(int y = minY; y <= maxY; y++) {
                locations.add(new Location(x,y));
            }
        }
    }

    private void setupCoordinates(double[][] coords) {
        char label = 'a';
        for (double[] coord: coords) {
            Coordinate coordinate = new Coordinate(label, coord[0], coord[1]);
            if(coordinate.x < minX) minX = coordinate.x;
            if(coordinate.x > maxX) maxX = coordinate.x;
            if(coordinate.y < minY) minY = coordinate.y;
            if(coordinate.y > maxY) maxY = coordinate.y;
            coordinates.add(coordinate);
            label++;
        }
    }

    public void loadCoords(List<String> coords) {
        //x,y
        double[][] dCoords = new double[coords.size()][2];
        for(int i = 0; i < coords.size(); i++) {
            String input = coords.get(i);
            if(input.contains(",")) {
                int x = Integer.parseInt(input.substring(0, input.indexOf(",")).trim());
                int y = Integer.parseInt(input.substring(input.indexOf(",") + 1).trim());
                dCoords[i][0] = x;
                dCoords[i][1] = y;
            }
        }
        loadCoords(dCoords);
    }

    public Map<String, Location> getLocationMap() {
        Map<String, Location> locationMap = new HashMap<>();
        for(Location location: locations) {
            locationMap.put(location.x+","+location.y, location);
        }
        return locationMap;
    }

    public int sizeRegionLessThan(int maxDistance) {
        List<Location> region = new ArrayList<>();
        for(Location location: locations) {
            if(location.getDistanceToAll() < maxDistance) {
                region.add(location);
            }
        }
        return region.size();
    }

    public class Location {
        public int x;
        public int y;
        public List<Coordinate> closest = new ArrayList<>();

        public double[] getPosition() {
            return new double[]{x,y};
        }

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Location{" +
                    "x=" + x +
                    ", y=" + y +
                    ", closest=" + closest +
                    '}';
        }

        public int getDistanceToAll() {
            int sum = 0;
            for(Coordinate coordinate: coordinates) {
                sum += DistanceUtil.manhattanDistance(this.getPosition(),coordinate.getPosition());
            }
            return sum;
        }
    }

    private class Coordinate {
        public char label = 0;
        public int x;
        public int y;
        List<Location> closest = new ArrayList<>();

        public double[] getPosition() {
            return new double[]{x,y};
        }

        @Override
        public String toString() {
            return "Coordinate{" +
                    "label=" + label +
                    ", x=" + x +
                    ", y=" + y +
                    ", closest=" + closest +
                    '}';
        }

        public Coordinate(char label, double x, double y) {
            this.label = label;
            this.x = (int) x;
            this.y = (int) y;
        }

        public int area() {
            return closest.size();
        }
    }


}
