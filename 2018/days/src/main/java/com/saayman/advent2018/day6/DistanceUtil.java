package com.saayman.advent2018.day6;

import org.apache.commons.math3.ml.distance.DistanceMeasure;
import org.apache.commons.math3.ml.distance.ManhattanDistance;

public class DistanceUtil {
    public static int manhattanDistance(double[] first, double[] second) {
        DistanceMeasure measure = new ManhattanDistance();
        return (int) measure.compute(first, second);
    }
}
