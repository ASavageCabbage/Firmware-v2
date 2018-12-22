package model;

public class Battery {

    // Specifications
    private double maxCurrent; // Units: Amps
    private double outVoltage; // Units: Volts
    private double capacity; // Units: Joules
    private double mass; // Units: Kilograms

    // Getters and Setters

    public double getMaxCurrent() {
        return maxCurrent;
    }

    public void setMaxCurrent(double maxCurrent) {
        this.maxCurrent = maxCurrent;
    }

    public double getOutVoltage() {
        return outVoltage;
    }

    public void setOutVoltage(double outVoltage) {
        this.outVoltage = outVoltage;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }
}
