package model;

public class Motor {

    // Specifications
    private double nominalVoltage; // Units: Volts
    private double peakPower; // Units: Watts
    private double continuousPower; // Units: Watts
    private double noLoadSpeed; // Units: Hertz
    private double peakTorque; // Units: Newton-Metres
    private double mass; // Units: Kilograms

    // Getters and Setters

    public double getNominalVoltage() {
        return nominalVoltage;
    }

    public void setNominalVoltage(double nominalVoltage) {
        this.nominalVoltage = nominalVoltage;
    }

    public double getPeakPower() {
        return peakPower;
    }

    public void setPeakPower(double peakPower) {
        this.peakPower = peakPower;
    }

    public double getContinuousPower() {
        return continuousPower;
    }

    public void setContinuousPower(double continuousPower) {
        this.continuousPower = continuousPower;
    }

    public double getNoLoadSpeed() {
        return noLoadSpeed;
    }

    public void setNoLoadSpeed(double noLoadSpeed) {
        this.noLoadSpeed = noLoadSpeed;
    }

    public double getPeakTorque() {
        return peakTorque;
    }

    public void setPeakTorque(double peakTorque) {
        this.peakTorque = peakTorque;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

// Methods that actually do stuff


}
