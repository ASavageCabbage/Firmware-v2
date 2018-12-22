package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.List;

public class SolarCar {

    private StringProperty name = new SimpleStringProperty();
    private IntegerProperty maxSpeed = new SimpleIntegerProperty();
    private IntegerProperty batteryLife = new SimpleIntegerProperty();
    private IntegerProperty totalMass = new SimpleIntegerProperty();

    private AeroShell aeroShell;
    private Arrays arrays;
    private Battery battery;
    private Motor motor;
    private List<PoweredComponent> components;
    private Wheel wheels;

    private double driverMass; // Units: Kilograms

    // Getters and Setters

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getMaxSpeed() {
        return maxSpeed.get();
    }

    public IntegerProperty maxSpeedProperty() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed.set(maxSpeed);
    }

    public int getBatteryLife() {
        return batteryLife.get();
    }

    public IntegerProperty batteryLifeProperty() {
        return batteryLife;
    }

    public void setBatteryLife(int batteryLife) {
        this.batteryLife.set(batteryLife);
    }

    public int getTotalMass() {
        return totalMass.get();
    }

    public IntegerProperty totalMassProperty() {
        return totalMass;
    }

    public void setTotalMass(int totalMass) {
        this.totalMass.set(totalMass);
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


}
