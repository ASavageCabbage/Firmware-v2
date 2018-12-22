package model;

import java.util.List;

public class SolarCar {

    private String name;
    private double maxSpeed;
    private double batteryLife;
    private double totalMass;

    private AeroShell aeroShell;
    private Arrays arrays;
    private Battery battery;
    private Motor motor;
    private List<PoweredComponent> components;
    private Wheel wheels;

    private double driverMass; // Units: Kilograms

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public double getBatteryLife() {
        return batteryLife;
    }

    public void setBatteryLife(double batteryLife) {
        this.batteryLife = batteryLife;
    }

    public double getTotalMass() {
        return totalMass;
    }

    public void setTotalMass(double totalMass) {
        this.totalMass = totalMass;
    }

    public AeroShell getAeroShell() {
        return aeroShell;
    }

    public void setAeroShell(AeroShell aeroShell) {
        this.aeroShell = aeroShell;
    }

    public Arrays getArrays() {
        return arrays;
    }

    public void setArrays(Arrays arrays) {
        this.arrays = arrays;
    }

    public Battery getBattery() {
        return battery;
    }

    public void setBattery(Battery battery) {
        this.battery = battery;
    }

    public Motor getMotor() {
        return motor;
    }

    public void setMotor(Motor motor) {
        this.motor = motor;
    }

    public List<PoweredComponent> getComponents() {
        return components;
    }

    public void setComponents(List<PoweredComponent> components) {
        this.components = components;
    }

    public Wheel getWheels() {
        return wheels;
    }

    public void setWheels(Wheel wheels) {
        this.wheels = wheels;
    }

    public double getDriverMass() {
        return driverMass;
    }

    public void setDriverMass(double driverMass) {
        this.driverMass = driverMass;
    }

    // Methods that actually do stuff

    //TODO: Implement
    // EFFECTS: Returns true if all the car's components have had their specs properly set.
    public boolean isValid() {
        return false;
    }

    //TODO: Implement
    // EFFECTS: Returns an error message detailing issues with car component states.
    public String errorMessage() {
        return "";
    }
}
