package com.saayman.advent2019.day1;

public class Module {
    private String id;

    public void setMass( int mass) {
        this.mass = mass;
    }

    private int mass;

    public Module(String id,int mass) {
        this.id = id;
        this.mass = mass;
    }

    public int requiredFuelForMass() {
        return fuelCalc(mass);
    }


    private int fuelCalc(int massInput) {
        int step1 = (int) Math.floor(massInput / 3.0);
        int step2 = step1 - 2;
        return step2;
    }

    public int requiredFuel() {
        return requiredFuelForMass()+ fuelForFuel(requiredFuelForMass());
    }

    private int fuelForFuel(int requiredFuel) {
        if(fuelCalc(requiredFuel) >= 0) {
            return fuelCalc(requiredFuel) + fuelForFuel(fuelCalc(requiredFuel));
        } else {
            return 0;
        }
    }
}
